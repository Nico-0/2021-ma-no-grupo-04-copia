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
        Boolean isPendiente = publicacion.isPendiente();
        Boolean awaitingConfirmation = publicacion.getPendienteConfirmacion();
        UsuarioDuenio interesado = publicacion.getInteresado();
        boolean esDuenio = publicacion.getAutor().getUsername().equals(username);
        boolean esComun = tipoUsuario.equals("comun");
        Boolean puedeAdoptar = esDuenio || !esComun;
        boolean esReservador;
        if(interesado != null){
            esReservador = interesado.getUsername().equals(username);
        } else esReservador = false;
        viewModel.put("isPublicada", isPublicada);
        viewModel.put("isFinalizada", isFinalizada);
        viewModel.put("isPendiente", isPendiente);
        viewModel.put("awaitingConfirmation", awaitingConfirmation);
        viewModel.put("esReservador", esReservador);
        viewModel.put("puedeAdoptar", puedeAdoptar);

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
         publicacion.reservarMascota(nuevo_duenio);
        }
        else {//igual si no es comun no sale el boton
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
            nuevo_duenio = (UsuarioDuenio) repoUsers.obtenerUsuario(username);
            publicacion = (PublicacionPerdida) repoPublis.getDeID(id_publi);

            duenio_original = publicacion.getAutor();
            verificarCreador(duenio_original, nuevo_duenio);
            publicacion.reservarMascota(nuevo_duenio);
        }
        else { //igual si no es comun no sale el boton
            throw new RuntimeException("Solo un usuario de tipo comun puede recuperar");
        }


        response.redirect("/publicaciones/"+id_publi);

        return null;
    }

    private static void verificarCreador(UsuarioDuenio autor, UsuarioDuenio consumidor){
        if(autor.equals(consumidor)){
            throw new RuntimeException("No puede adoptar/recuperar el creador de la publicacion"); //igual esta oculto el boton para el creador
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

    public static Void cancelarReserva(Request request, Response response, EntityManager em) {

        String id_publi = request.params(":id");

        String username = request.cookie("username");

        UsuarioDuenio duenio_original;
        UsuarioDuenio interesado_actual;
        UsuarioDuenio cancelante;
        Publicacion publicacion;
        GeneradorUsuario repoUsers = new GeneradorUsuario(em);
        PublicacionService repoPublis = new PublicacionService(em);


        cancelante = (UsuarioDuenio) repoUsers.obtenerUsuario(username);
        publicacion = repoPublis.getDeID(id_publi);
        interesado_actual = publicacion.getInteresado();
        duenio_original = publicacion.getAutor();

        if(cancelante.equals(interesado_actual) || cancelante.equals(duenio_original)){
            publicacion.cancelarReserva();
        } else { //igual esta oculto el boton para el que no sea
            throw new RuntimeException("Solo puede cancelar el interesado o dueño original");
        }

        response.redirect("/publicaciones/"+id_publi);

        return null;
    }

    public static Void aceptar(Request request, Response response, EntityManager em) {

        String id_publi = request.params(":id");

        String username = request.cookie("username");

        UsuarioDuenio duenio_original;
        UsuarioDuenio aceptante;
        Publicacion publicacion;
        GeneradorUsuario repoUsers = new GeneradorUsuario(em);
        PublicacionService repoPublis = new PublicacionService(em);

        aceptante = (UsuarioDuenio) repoUsers.obtenerUsuario(username);
        publicacion = repoPublis.getDeID(id_publi);
        duenio_original = publicacion.getAutor();

        if(aceptante.equals(duenio_original)){
            publicacion.adoptarMascota(publicacion.getInteresado());
        } else { //igual solo ve el boton el duenio
            throw new RuntimeException("Solo aceptar el dueño original");
        }

        response.redirect("/publicaciones/"+id_publi);

        return null;
    }
}
