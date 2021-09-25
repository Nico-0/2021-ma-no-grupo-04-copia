package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Aprobar {
    public static ModelAndView aprobar(Request request, Response response){
        String tipoUsuario = request.cookie("tipoUsuario");
        if(tipoUsuario.equals("voluntario"))
            return new ModelAndView(null, "aprobar.hbs");
        else
            return new ModelAndView(null, "aprobarFail.hbs");
    }
}
