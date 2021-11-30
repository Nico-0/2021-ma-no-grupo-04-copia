package com.dds.rescate.controllers;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.RepoAsociacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
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
}
