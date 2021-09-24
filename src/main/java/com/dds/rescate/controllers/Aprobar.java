package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Aprobar {
    public static ModelAndView aprobar(Request req, Response res){
        return new ModelAndView(null, "aprobar.hbs");
    }
}
