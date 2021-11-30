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

import javax.persistence.EntityManager;

public class MuroPublicaciones {

    private static int CANT_POR_PAG = 5;

    public static ModelAndView muro(Request request, Response response, EntityManager em) {
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("Titulo", "Lista publicaciones");

        String estado = request.queryParams("estado");
        List<Publicacion> publicaciones;

        PublicacionService repo = new PublicacionService(em);
        if(estado != null && estado.equals("finalizadas"))
            publicaciones = repo.getFinalizadas();
        else
            publicaciones = repo.getPublicadas();


        publicaciones = publicaciones.stream().filter(publicacion -> !publicacion.getTipoPubli().equals("intencion")).collect(Collectors.toList());

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

        //paginacion (seria mas optimo limitar la cantidad antes de haber procesado la tabla entera, quizas se puede hacer en un paso con una query SQL)
        int cant_elementos = publicaciones.size();
        int nro_pag = Integer.parseInt(request.params(":nro_pag"));
        viewModel.put("nro_pag", nro_pag);
        int offset = CANT_POR_PAG * nro_pag;
        publicaciones = publicaciones.stream().skip(offset).limit(CANT_POR_PAG).collect(Collectors.toList());

        Boolean ultima_pag = ((nro_pag + 1) * CANT_POR_PAG) < (cant_elementos);
        viewModel.put("ultima_pag", ultima_pag);

        viewModel.put("Publicaciones", publicaciones);

        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);
        return new ModelAndView(viewModel, "muro.hbs");
    }

    public static Void muroSinPag(Request request, Response response, EntityManager em){
        response.redirect("/muro/0");
        return null;
    }
}
