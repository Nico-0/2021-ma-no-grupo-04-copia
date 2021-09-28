package com.dds.rescate.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.service.PublicacionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MuroPublicaciones {

    public static ModelAndView muro(Request request, Response response) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("Titulo", "Lista publicaciones");

        List<Publicacion> publicaciones = PublicacionService.getInstance().getPublicadas();

        String animal = request.queryParams("animal");
        String tipoPubli = request.queryParams("tipoPubli");
        String orden = request.queryParams("orden");

        if(animal != null && !(animal.equals("all"))) {
            publicaciones = publicaciones.stream().filter(publicacion -> publicacion.getTipoMascotaString().equals(animal)).collect(Collectors.toList());
        }

        if(tipoPubli != null && !(tipoPubli.equals("all"))) {
            publicaciones = publicaciones.stream().filter(publicacion -> publicacion.getTipoPubli().equals(tipoPubli)).collect(Collectors.toList());
        }

        if(orden != null) {
            switch (orden) {
                case "autor":
                    publicaciones = publicaciones.stream().sorted(Comparator.comparing(Publicacion::getAutorString)).collect(Collectors.toList());
                    break;
                case "asociacion":
                    publicaciones = publicaciones.stream().sorted(Comparator.comparing(Publicacion::getAsociacionString)).collect(Collectors.toList());
                    break;
                case "fecha":
                    publicaciones = publicaciones.stream().sorted(Comparator.comparing(Publicacion::getFechaString).reversed()).collect(Collectors.toList());
                    break;
                default:
                    break;
            }
        }

        viewModel.put("Publicaciones", publicaciones);
        return new ModelAndView(viewModel, "muro.hbs");
    }
}
