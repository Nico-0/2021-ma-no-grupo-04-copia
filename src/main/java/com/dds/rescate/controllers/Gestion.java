package com.dds.rescate.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Gestion {
    public static ModelAndView gestion(Request req, Response res){
        return new ModelAndView(null, "gestion.hbs");
    }
}
