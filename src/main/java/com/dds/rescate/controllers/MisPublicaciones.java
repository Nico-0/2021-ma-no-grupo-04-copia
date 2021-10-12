package com.dds.rescate.controllers;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MisPublicaciones {
    public static ModelAndView show(Request request, Response response){
        HashMap<String, Object> viewMisPublicaciones = new HashMap<>();

        String username = request.cookie("username");
        GeneradorUsuario repoUsuarios = GeneradorUsuario.getInstance();
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        String nombrePerfil = user.getNombre();

        List<Publicacion> publicaciones = PublicacionService.getInstance().getPublicadas();

        if(publicaciones.size() > 0){
            publicaciones = publicaciones.stream().filter(publicacion -> publicacion.getAutorString().equals(nombrePerfil)).collect(Collectors.toList());
        }
        viewMisPublicaciones.put("Publicaciones", publicaciones);
        return new ModelAndView(viewMisPublicaciones, "misPublicaciones.hbs");
    }
}
