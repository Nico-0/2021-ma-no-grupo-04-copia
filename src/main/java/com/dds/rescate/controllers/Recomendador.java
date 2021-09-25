package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Recomendador {
    public static ModelAndView recomendador(Request request, Response response){
        return new ModelAndView(null, "recomendacion.hbs");
    }
}
