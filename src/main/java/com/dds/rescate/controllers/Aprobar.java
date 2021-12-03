package com.dds.rescate.controllers;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.UsuarioAdministrador;
import com.dds.rescate.model.UsuarioVoluntario;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class Aprobar {
    public static ModelAndView show(Request request, Response response, EntityManager em){
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        if(tipoUsuario.equals("voluntario")) {
            GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
            UsuarioVoluntario user = (UsuarioVoluntario) repoUsuarios.obtenerUsuario(username);
            viewModel.put("nombreAsoc", user.asociacion.getNombre());

            List<Publicacion> pendientes;
            PublicacionService repoPublis = new PublicacionService(em);

            pendientes = repoPublis.getPendientes(user.asociacion);

            viewModel.put("Pendientes", pendientes);

            return new ModelAndView(viewModel, "aprobar.hbs");
        }else
            return new ModelAndView(viewModel, "aprobarFail.hbs");
    }

    public static Void aprobar(Request request, Response response, EntityManager em) {

        String id_publi = request.params(":id");
        String tipoUsuario = request.cookie("tipoUsuario");

        Publicacion publicacion;
        PublicacionService repoPublis = new PublicacionService(em);

        publicacion = repoPublis.getDeID(id_publi);

        if(!tipoUsuario.equals("voluntario")){
            throw new RuntimeException("Solo un usuario voluntario puede aprobar");
        }

        publicacion.aprobar();

        response.redirect("/publicaciones/"+id_publi);

        return null;
    }
}
