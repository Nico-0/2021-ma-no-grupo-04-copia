package com.dds.rescate.controllers;

import com.dds.rescate.model.Enum.EstadoPubli;
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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RecomendadorController {
    public static ModelAndView recomendador(Request request, Response response, EntityManager em){
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        PublicacionService repo = new PublicacionService(em);
        List<Publicacion> publicaciones = repo.getDeTipo("intencion");
        publicaciones = publicaciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());
        //publicaciones.forEach(Publicacion::ordenarRecomendaciones);

        viewModel.put("Intenciones", publicaciones);

        GeneradorUsuario repoUser = new GeneradorUsuario(em);
        String id_user = repoUser.getIdByUsername(username);
        viewModel.put("id_user", id_user);


        return new ModelAndView(viewModel, "recomendacion.hbs");
    }


    public static Void recomendar(Request request, Response response, EntityManager em) {

        //recomienda a todos, para solo un usuario usar el de json

        //List<Publicacion> intenciones = PublicacionService.getInstance().getDeTipo("intencion");
        //intenciones = intenciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());

        PublicacionService repo = new PublicacionService(em);
        List<Publicacion> intenciones = repo.getDeTipo("intencion");

        Recomendador reco = new Recomendador(em);
        intenciones.forEach(intencion -> reco.recomendar((PublicacionIntencionDeAdopcion) intencion));

        response.redirect("/recomendador");

        return null;
    }

    public static Object get_json(Request request, Response response, EntityManager em){

        String id_user = request.params(":id_user");
        response.header("FOO", "bar");

        GeneradorUsuario repo = new GeneradorUsuario(em);

        try {
            UsuarioDuenio user = (UsuarioDuenio) repo.getUserByID(Integer.parseInt(id_user));

            Recomendacion_API reco = new Recomendacion_API(user, em);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(reco);

            response.type("application/json");
            response.status(202);
            response.body(json);

            //System.out.println(response.body());

        }
        catch (NoResultException nre) {
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
