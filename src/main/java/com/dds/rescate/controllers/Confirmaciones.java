package com.dds.rescate.controllers;

import com.dds.rescate.model.*;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Confirmaciones {

    public static ModelAndView show(Request request, Response response, EntityManager em){
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);
        PublicacionService repoPublis = new PublicacionService(em);

        List<Publicacion> publicaciones = repoPublis.getPublicadasParaConfirmar(user);
        List<PublicacionAdopcion> Adopciones = publicaciones.stream().filter(p -> p instanceof PublicacionAdopcion).map(p -> (PublicacionAdopcion) p).collect(Collectors.toList());
        List<PublicacionPerdida> Perdidas = publicaciones.stream().filter(p -> p instanceof PublicacionPerdida).map(p -> (PublicacionPerdida) p).collect(Collectors.toList());
        List<ChapitaEncontrada> Encontradas = repoPublis.getChapitasPublicadas(user);

        int vacio = Adopciones.size() + Perdidas.size() + Encontradas.size();
        viewModel.put("Adopciones", Adopciones);
        viewModel.put("Perdidas", Perdidas);
        viewModel.put("Encontradas", Encontradas);
        viewModel.put("vacio", vacio);

        return new ModelAndView(viewModel, "confirmacion.hbs");
    }
}
