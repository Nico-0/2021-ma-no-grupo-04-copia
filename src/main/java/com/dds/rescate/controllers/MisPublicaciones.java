package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MisPublicaciones {
    public static ModelAndView show(Request request, Response response){
        return new ModelAndView(null, "misPublicaciones.hbs");
    }
}
