package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Perfil {
    public static ModelAndView perfil(Request req, Response res){
        return new ModelAndView(null, "perfil.hbs");
    }
}
