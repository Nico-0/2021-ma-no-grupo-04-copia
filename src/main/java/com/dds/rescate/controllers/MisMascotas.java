package com.dds.rescate.controllers;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.server.Data;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.RepoAsociacion;
import com.dds.rescate.service.RepoMascota;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

public class MisMascotas {
    public static ModelAndView show(Request request, Response response, EntityManager em){
        HashMap<String, Object> viewMascotas = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewMascotas.put("username", username);
        viewMascotas.put("tipoUsuario", tipoUsuario);

        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        //System.out.print(user);

        //Mascotas de usuario
        List<Mascota> mascotas = user.getMascotasSinPublicar();
        List<Mascota> mascotasSinPerder = mascotas.stream().filter(m -> !m.estaPerdida()).collect(Collectors.toList());
        List<Mascota> mascotasPerdidas = mascotas.stream().filter(Mascota::estaPerdida).collect(Collectors.toList());
        viewMascotas.put("Mascotas", mascotasSinPerder);
        viewMascotas.put("Perdidas", mascotasPerdidas);
        List<Mascota> mascotasComprometidas = user.getMascotasComprometidas();
        viewMascotas.put("MascotasComprometidas", mascotasComprometidas);
        /*for(Mascota  masc : mascotas){
            System.out.println(masc.toString());
        }*/

        return new ModelAndView(viewMascotas, "misMascotas.hbs");
    }

    public static Void registrarGenerica(Request request, Response response, EntityManager em) {

        String username = request.cookie("username");
        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        RepoAsociacion repoAsociacion = new RepoAsociacion(em);
        Asociacion asociacion_1 = repoAsociacion.getAsociacion1();
        List<Respuesta> caracteristicas = repoAsociacion.crearRespuestasGenericasCaracAsoc1();

        String apodo = Integer.toString(Calendar.getInstance().get(Calendar.MILLISECOND));
        Mascota mascota = new Mascota(TipoMascota.GATO, "Generica", apodo, "Me sentia con ganas de tener una mascota adicional", asociacion_1, caracteristicas, Sexo.HEMBRA,"guess.jpg");


        em.persist(mascota);
        user.agregarMascota(mascota);

        response.redirect("/mis_mascotas");
        return null;
    }

    public static Void darAdopcion(Request request, Response response, EntityManager em) {

        String username = request.cookie("username");
        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        RepoAsociacion repoAsociacion = new RepoAsociacion(em);
        Asociacion asociacion_1 = repoAsociacion.getAsociacion1();
        List<Respuesta> preguntas = repoAsociacion.crearRespuestasGenericasPregAsoc1();

        String id_mascota = request.params(":id");

        RepoMascota repoMascota = new RepoMascota(em);
        Mascota mascota = repoMascota.getMascotaByID(Integer.parseInt(id_mascota));

        PublicacionAdopcion adopcion_1 = new PublicacionAdopcion(user, asociacion_1, mascota, preguntas);


        em.persist(adopcion_1);


        response.redirect("/mis_mascotas");
        return null;
    }

    public static Void perdida(Request request, Response response, EntityManager em) {

        String id_mascota = request.params(":id");
        RepoMascota repoMascota = new RepoMascota(em);
        Mascota mascota = repoMascota.getMascotaByID(Integer.parseInt(id_mascota));

        mascota.perder();

        response.redirect("/mis_mascotas");
        return null;
    }

    public static Void aceptar(Request request, Response response, EntityManager em) {

        String id_mascota = request.params(":id");
        RepoMascota repoMascota = new RepoMascota(em);
        Mascota mascota = repoMascota.getMascotaByID(Integer.parseInt(id_mascota));

        mascota.recuperar();
        ChapitaEncontrada chapita = repoMascota.getChapita(mascota.getChapita());
        chapita.finalizar();

        response.redirect("/mis_mascotas");
        return null;
    }

    public static Void rechazar(Request request, Response response, EntityManager em) {

        String id_mascota = request.params(":id");
        RepoMascota repoMascota = new RepoMascota(em);
        Mascota mascota = repoMascota.getMascotaByID(Integer.parseInt(id_mascota));

        ChapitaEncontrada chapita = repoMascota.getChapita(mascota.getChapita());
        chapita.finalizar();

        response.redirect("/mis_mascotas");
        return null;
    }


}
