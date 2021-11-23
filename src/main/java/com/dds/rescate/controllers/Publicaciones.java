package com.dds.rescate.controllers;

import com.dds.rescate.model.*;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;

import java.util.HashMap;


public class Publicaciones {

    public static ModelAndView show(Request request, Response response, EntityManager em) {
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);


        String id_publi = request.params(":id");

        PublicacionService repo = new PublicacionService(em);
        Publicacion publicacion = repo.getDeID(id_publi);
        viewModel.put("Publicacion", publicacion);

        String tipo_publi = publicacion.getTipoPubli();
        Boolean isPublicada = publicacion.isPublicada();
        Boolean isFinalizada = publicacion.isFinalizada();
        viewModel.put("isPublicada", isPublicada);
        viewModel.put("isFinalizada", isFinalizada);

        switch (tipo_publi) {
            case "perdida":
                return new ModelAndView(viewModel, "paginaPubliPerdida.hbs");
            case "adopcion":
                return new ModelAndView(viewModel, "paginaPubliAdopcion.hbs");
            case "intencion":
                return new ModelAndView(viewModel, "paginaPubliIntencion.hbs");
            default:
                return new ModelAndView(null, null);
        }


    }

    public static Void adoptar(Request request, Response response, EntityManager em) {

        String id_publi = request.params(":id");

        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");

        UsuarioDuenio duenio_original;
        UsuarioDuenio nuevo_duenio;
        PublicacionAdopcion publicacion;
        GeneradorUsuario repoUsers = new GeneradorUsuario(em);
        PublicacionService repoPublis = new PublicacionService(em);

        if(tipoUsuario.equals("comun")){
         nuevo_duenio = (UsuarioDuenio) repoUsers.obtenerUsuario(username);
         publicacion = (PublicacionAdopcion) repoPublis.getDeID(id_publi);

         duenio_original = publicacion.getAutor();
         verificarCreador(duenio_original, nuevo_duenio);
         publicacion.adoptarMascota(nuevo_duenio);
        }
        else {
            throw new RuntimeException("Solo un usuario de tipo comun puede adoptar");
        }


        response.redirect("/publicaciones/"+id_publi);

        return null;
    }

    public static Void recuperar(Request request, Response response, EntityManager em) {

        String id_publi = request.params(":id");

        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");

        PublicacionPerdida publicacion;
        UsuarioDuenio duenio_original;
        UsuarioDuenio nuevo_duenio;
        GeneradorUsuario repoUsers = new GeneradorUsuario(em);
        PublicacionService repoPublis = new PublicacionService(em);

        if(tipoUsuario.equals("comun")){
            publicacion = (PublicacionPerdida) repoPublis.getDeID(id_publi);

            nuevo_duenio = (UsuarioDuenio) repoUsers.obtenerUsuario(username);
            duenio_original = publicacion.getAutor();
            verificarCreador(duenio_original, nuevo_duenio);

            publicacion.recuperarMascota(nuevo_duenio);
        }
        else {
            throw new RuntimeException("Solo un usuario de tipo comun puede recuperar");
        }


        response.redirect("/publicaciones/"+id_publi);

        return null;
    }

    private static void verificarCreador(UsuarioDuenio autor, UsuarioDuenio consumidor){
        if(autor.equals(consumidor)){
            throw new RuntimeException("No puede adoptar/recuperar el creador de la publicacion");
        }
    }

    public static Void finalizar(Request request, Response response, EntityManager em) {

        String id_publi = request.params(":id");

        PublicacionIntencionDeAdopcion publicacion;
        PublicacionService repoPublis = new PublicacionService(em);

        publicacion = (PublicacionIntencionDeAdopcion) repoPublis.getDeID(id_publi);

        publicacion.darDeBaja();


        response.redirect("/publicaciones/"+id_publi);

        return null;
    }
}
