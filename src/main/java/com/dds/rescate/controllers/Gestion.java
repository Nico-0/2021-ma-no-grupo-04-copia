package com.dds.rescate.controllers;

import com.dds.rescate.model.UsuarioAdministrador;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.HashMap;

public class Gestion {
    public static ModelAndView gestion(Request request, Response response, EntityManager em) {
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        if (tipoUsuario.equals("administrador")) {
            try {
                GeneradorUsuario repoUsuarios = new GeneradorUsuario(em);
                UsuarioAdministrador user = (UsuarioAdministrador) repoUsuarios.obtenerUsuario(username);
                viewModel.put("nombreAsoc", user.asociacion.getNombre());
                viewModel.put("caracteristicas", user.asociacion.getCaracteristicas());
                viewModel.put("preguntas", user.asociacion.getPreguntas());
            }catch (RuntimeException no_existe_usuario){
                viewModel.put("nombreAsoc", "Se acaba de eliminar el usuario, vuelva a cargar datos");
            }
            return new ModelAndView(viewModel, "gestion.hbs");
        }
        else
            return new ModelAndView(viewModel, "gestionFail.hbs");
    }
}
