package com.dds.rescate.controllers;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MisPublicaciones {
    public static ModelAndView show(Request request, Response response, EntityManager em){
        HashMap<String, Object> viewMisPublicaciones = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewMisPublicaciones.put("username", username);
        viewMisPublicaciones.put("tipoUsuario", tipoUsuario);

        GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        String nombrePerfil = user.getNombre();

        PublicacionService repoPublis = new PublicacionService(em);
        List<Publicacion> publicaciones = repoPublis.getPublicadas();
        //TODO mostrar publicaciones finalizadas?

        if(publicaciones.size() > 0){
            publicaciones = publicaciones.stream().filter(publicacion -> publicacion.getAutorString().equals(nombrePerfil)).collect(Collectors.toList());
        }
        viewMisPublicaciones.put("Publicaciones", publicaciones);
        return new ModelAndView(viewMisPublicaciones, "misPublicaciones.hbs");
    }
}
