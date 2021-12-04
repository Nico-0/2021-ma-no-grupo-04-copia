package com.dds.rescate.controllers;

import com.dds.rescate.exception.ChapitaException;
import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.RepoAsociacion;
import com.dds.rescate.service.RepoMascota;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MisPublicaciones {
    public static ModelAndView show(Request request, Response response, EntityManager em){
        HashMap<String, Object> viewMisPublicaciones = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewMisPublicaciones.put("username", username);
        viewMisPublicaciones.put("tipoUsuario", tipoUsuario);

        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);


        PublicacionService repoPublis = new PublicacionService(em);
        List<Publicacion> publicaciones = repoPublis.getPublicacionesBy(user, EstadoPubli.PUBLICADA);
        List<Publicacion> pendientes = repoPublis.getPublicacionesBy(user, EstadoPubli.PENDIENTE);
        List<Publicacion> finalizadas = repoPublis.getPublicacionesBy(user, EstadoPubli.FINALIZADA);


        viewMisPublicaciones.put("Publicaciones", publicaciones);
        viewMisPublicaciones.put("Pendientes", pendientes);
        viewMisPublicaciones.put("Finalizadas", finalizadas);
        return new ModelAndView(viewMisPublicaciones, "misPublicaciones.hbs");
    }

    public static Void publicarPerdidaGenerica(Request request, Response response, EntityManager em) {

        String username = request.cookie("username");
        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user_1 = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        RepoAsociacion repoAsociacion = new RepoAsociacion(em);
        Asociacion asociacion_1 = repoAsociacion.getAsociacion1();
        List<Respuesta> caracteristicas = repoAsociacion.crearRespuestasGenericasCaracAsoc1();

        String apodo = Integer.toString(Calendar.getInstance().get(Calendar.MILLISECOND));
        Mascota mascota = new Mascota(TipoMascota.PERRO, "A_Definir", apodo, "Será perro?", asociacion_1, caracteristicas, Sexo.HEMBRA, "guess.png");

        PublicacionPerdida perdida_1 = new PublicacionPerdida(user_1, asociacion_1, mascota, EstadoEncontrada.EXCELENTE, "Iba por la calle y apareció este animal ???", user_1.getContacto());

        em.persist(mascota);
        em.persist(perdida_1);

        response.redirect("/mis_publicaciones");
        return null;
    }

    public static Void publicarIntencionGenerica(Request request, Response response, EntityManager em) {

        String username = request.cookie("username");
        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user_1 = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        RepoAsociacion repoAsociacion = new RepoAsociacion(em);
        Asociacion asociacion_1 = repoAsociacion.getAsociacion1();
        List<Respuesta> caracteristicas = repoAsociacion.crearRespuestasGenericasCaracAsoc1();
        List<Respuesta> preguntas = repoAsociacion.crearRespuestasGenericasPregAsoc1();


        PublicacionIntencionDeAdopcion intencion_1 = new PublicacionIntencionDeAdopcion(user_1, asociacion_1, TipoMascota.MINIPIG, caracteristicas, preguntas);

        em.persist(intencion_1);

        response.redirect("/recomendador");
        return null;
    }

    public static Void notificar(Request request, Response response, EntityManager em) {

        String ID_chapita = request.queryParams("ID_chapita");

        String username = request.cookie("username");
        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user_1 = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        //verificar chapita no publicada
        ChapitaEncontrada chapita;
        try {
        chapita = em.createQuery("from ChapitaEncontrada c where c.id_chapita = ?1 and c.publicacion_finalizada = ?2", ChapitaEncontrada.class)
                .setParameter(1, ID_chapita)
                .setParameter(2, false)
                .getSingleResult();
        }catch (NoResultException nre){
            //do nothing
            chapita = null;
        }
        if(chapita != null){
            response.redirect("/mis_publicaciones#popupChapitaYaPublicada");
        }


        //crear publicacion de chapita
        try {
            ChapitaEncontrada chapitaNueva = new ChapitaEncontrada(user_1, ID_chapita, em);
            em.persist(chapitaNueva);
        }catch (NoResultException nre){
            response.redirect("/mis_publicaciones#popupChapitaInexistente");
        }catch (ChapitaException mascota_no_perdida){
            response.redirect("/mis_publicaciones#popupMascotaNoPerdida");
        }

        response.redirect("/mis_publicaciones#popupChapitaSuccess");
        return null;
    }


}
