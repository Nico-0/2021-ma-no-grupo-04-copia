package com.dds.rescate.controllers;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.Recomendador;
import com.dds.rescate.util.Recomendacion_API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RecomendadorController {
    public static ModelAndView recomendador(Request request, Response response){
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        List<Publicacion> publicaciones = PublicacionService.getInstance().getDeTipo("intencion");
        publicaciones = publicaciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());

        viewModel.put("Intenciones", publicaciones);

        String id_user = GeneradorUsuario.getInstance().getIdByUsername(username);
        viewModel.put("id_user", id_user);


        return new ModelAndView(viewModel, "recomendacion.hbs");
    }


    public static Void recomendar(Request request, Response response) {

        //TODO actualizar para que solo se recomiende al usuario actual

        List<Publicacion> intenciones = PublicacionService.getInstance().getDeTipo("intencion");
        intenciones = intenciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());

        intenciones.forEach(intencion -> Recomendador.getInstance().recomendar((PublicacionIntencionDeAdopcion) intencion));

        response.redirect("/recomendador");

        return null;
    }

    public static Object get_json(Request request, Response response){

        String id_user = request.params(":id_user");
        response.header("FOO", "bar");

        UsuarioDuenio user = (UsuarioDuenio) GeneradorUsuario.getInstance().getUserByID(Integer.parseInt(id_user));

        if(user != null) {

            Recomendacion_API reco = new Recomendacion_API(user);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(reco);

            response.type("application/json");
            response.status(202);
            response.body(json);

            //System.out.println(response.body());

        }
        else {
            response.type("application/json");
            response.status(404);
            response.body("{\n" +
                    "  \"usuario pedido\": \"no existe\",\n" +
                    "  \"siguiente paso\": \"poner bien el ID\"\n" +
                    "}");
        }

        return response.body();
    }
}
