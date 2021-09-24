package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MisMascotas {
    public static ModelAndView show(Request req, Response res){
        return new ModelAndView(null, "misMascotas.hbs");
    }
}
