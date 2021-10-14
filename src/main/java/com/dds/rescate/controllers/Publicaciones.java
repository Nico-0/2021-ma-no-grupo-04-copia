package com.dds.rescate.controllers;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.PublicacionAdopcion;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.Recomendador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Publicaciones {

    public static ModelAndView show(Request request, Response response) {
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);


        String id_publi = request.params(":id");

        Publicacion publicacion = PublicacionService.getInstance().getDeID(id_publi);
        viewModel.put("Publicacion", publicacion);

        String tipo_publi = publicacion.getTipoPubli();
        Boolean isPublicada = publicacion.isPublicada();
        Boolean isFinalizada = publicacion.isFinalizada();
        viewModel.put("isPublicada", isPublicada);
        viewModel.put("isFinalizada", isFinalizada);

        switch (tipo_publi) {
            case "perdida":
                return new ModelAndView(viewModel, "paginaPubliPerdida.hbs");
            case "adopcion":
                return new ModelAndView(viewModel, "paginaPubliAdopcion.hbs");
            case "intencion":
                return new ModelAndView(viewModel, "paginaPubliIntencion.hbs");
            default:
                return new ModelAndView(null, null);
        }


    }

    public static Void adoptar(Request request, Response response) {

        String id_publi = request.params(":id");

        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");

        UsuarioDuenio nuevo_duenio;
        PublicacionAdopcion publicacion;

        if(tipoUsuario.equals("comun")){
         nuevo_duenio = (UsuarioDuenio) GeneradorUsuario.getInstance().obtenerUsuario(username);
         publicacion = (PublicacionAdopcion) PublicacionService.getInstance().getDeID(id_publi);
         publicacion.adoptarMascota(nuevo_duenio);
        }
        else {
            throw new RuntimeException("Solo un usuario de tipo comun puede adoptar");
        }


        response.redirect("/publicaciones/"+id_publi);

        return null;
    }
}
