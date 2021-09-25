package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Gestion {
    public static ModelAndView gestion(Request request, Response response) {
        String tipoUsuario = request.cookie("tipoUsuario");
        if (tipoUsuario.equals("administrador"))
            return new ModelAndView(null, "gestion.hbs");
        else
            return new ModelAndView(null, "gestionFail.hbs");
    }
}
