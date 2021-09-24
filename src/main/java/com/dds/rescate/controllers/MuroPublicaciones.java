package com.dds.rescate.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MuroPublicaciones {

    public static ModelAndView muro(Request request, Response response) {
        HashMap<String, Object> viewModel = new HashMap<>();
        List<Publicacion> publicaciones = PublicacionService.getInstance().getPendientes();
        viewModel.put("Titulo", "Lista publicaciones");
        viewModel.put("Publicaciones", publicaciones);

        return new ModelAndView(viewModel, "muro.hbs");
    }
}
