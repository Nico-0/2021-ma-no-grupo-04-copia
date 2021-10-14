package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class Gestion {
    public static ModelAndView gestion(Request request, Response response) {
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        if (tipoUsuario.equals("administrador"))
            return new ModelAndView(viewModel, "gestion.hbs");
        else
            return new ModelAndView(viewModel, "gestionFail.hbs");
    }
}
