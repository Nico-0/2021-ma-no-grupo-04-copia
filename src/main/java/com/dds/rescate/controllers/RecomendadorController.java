package com.dds.rescate.controllers;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.Recomendador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class RecomendadorController {
    public static ModelAndView recomendador(Request request, Response response){
        HashMap<String, Object> viewModel = new HashMap<>();

        List<Publicacion> publicaciones = PublicacionService.getInstance().getDeTipo("intencion");


        viewModel.put("Intenciones", publicaciones);

        return new ModelAndView(viewModel, "recomendacion.hbs");
    }


    public static Void recomendar(Request request, Response response) {

        //TODO actualizar para que solo se recomiende al usuario actual

        List<Publicacion> intenciones = PublicacionService.getInstance().getDeTipo("intencion");

        intenciones.forEach(intencion -> Recomendador.getInstance().recomendar((PublicacionIntencionDeAdopcion) intencion));

        response.redirect("/recomendador");

        return null;
    }
}
