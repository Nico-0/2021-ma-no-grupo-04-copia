package com.dds.rescate.controllers;

import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.MongoDB;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.Recomendador;
import com.dds.rescate.util.Recomendacion_API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.morphia.Datastore;
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
        List<Publicacion> publicaciones = repo.getIntencionesPublicadas(username);
        //publicaciones.forEach(Publicacion::ordenarRecomendaciones);

        viewModel.put("Intenciones", publicaciones);

        GeneradorUsuario repoUser = new GeneradorUsuario(em);
        String id_user = repoUser.getIdByUsername(username);
        viewModel.put("id_user", id_user);


        return new ModelAndView(viewModel, "recomendacion.hbs");
    }

    //recomendar para todos
    public static Void recomendar(Request request, Response response, EntityManager em) {

        recomendarTodos(em);

        response.redirect("/recomendador");
        return null;
    }

    //metodo intermedio para reusar en el job
    public static void recomendarTodos(EntityManager em){
        PublicacionService repoPubli = new PublicacionService(em);
        List<Publicacion> intenciones = repoPubli.getIntencionesPublicadas();

        Recomendador reco = new Recomendador(em);
        intenciones.forEach(intencion -> reco.recomendar((PublicacionIntencionDeAdopcion) intencion));

        //guardar json en mongo
        GeneradorUsuario repoUser = new GeneradorUsuario(em);
        List<UsuarioDuenio> users = repoUser.getUsuariosDuenio();
        users.forEach(u -> generarJson(u, em));
    }

    public static Void recomendarSingle(Request request, Response response, EntityManager em) {

        String username = request.cookie("username");
        String tipo = request.cookie("tipoUsuario");

        if(tipo.equals("comun")) {
            PublicacionService repoPubli = new PublicacionService(em);
            List<Publicacion> intenciones = repoPubli.getIntencionesPublicadas(username);

            Recomendador reco = new Recomendador(em);
            intenciones.forEach(intencion -> reco.recomendar((PublicacionIntencionDeAdopcion) intencion));

            //guardar json en mongo
            GeneradorUsuario repoUser = new GeneradorUsuario(em);
            UsuarioDuenio user = (UsuarioDuenio) repoUser.obtenerUsuario(username);
            generarJson(user, em);
        }
        else{
            throw new RuntimeException("Solo un usuario comun puede recibir recomendaciones");
        }

        response.redirect("/recomendador");

        return null;
    }

    private static void generarJson(UsuarioDuenio user, EntityManager em){
        Recomendacion_API recomendacion = new Recomendacion_API(user, em);
        MongoDB.getInstance().getDatastore().save(recomendacion);
    }

    public static Object get_json(Request request, Response response, EntityManager em){

        String id_user = request.params(":id_user");
        String tipo = request.cookie("tipoUsuario");
        response.header("FOO", "bar");

        if(!tipo.equals("comun")){
            throw new RuntimeException("Solo un usuario comun puede recibir recomendaciones");
        }

        try {
            GeneradorUsuario repoUser = new GeneradorUsuario(em);
            UsuarioDuenio user = (UsuarioDuenio) repoUser.getUserByID(Integer.parseInt(id_user));
            Recomendacion_API recomendacion = MongoDB.getInstance().getUltimaReco(user);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(recomendacion);

            response.type("application/json");
            response.status(202);
            response.body(json);

            //System.out.println(response.body());

        }
        catch (IndexOutOfBoundsException nre) {
            response.type("application/json");
            response.status(404);
            response.body("{\n" +
                    "  \"usuario pedido\": \"no existe recomendacion\",\n" +
                    "  \"siguiente paso\": \"poner bien el ID\"\n" +
                    "}");
        }

        return response.body();
    }
}
