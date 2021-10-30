package com.dds.rescate.controllers;
import com.dds.rescate.server.Data;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;

import static com.dds.rescate.controllers.LoginController.verificarLogin;

public class HomeController {
    public static ModelAndView home(Request request, Response response){

        String tipoUsuario = verificarLogin(request);
        if(tipoUsuario.equals("null")){
            return new ModelAndView(null, "homeSinLogin.hbs");
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("tipoUsuario", tipoUsuario);
        String username = request.cookie("username");
        viewModel.put("username", username);
        String nombrePersona = request.cookie("nombrePersona");
        viewModel.put("nombrePersona", nombrePersona);
        return new ModelAndView(viewModel, "home.hbs");


    }

    public static Void datos_base(Request request, Response response, EntityManager em) {

        Data data = new Data();
        data.init(em);

        response.redirect("/muro");
        return null;
    }

    public static Void datos_extra(Request request, Response response, EntityManager em) {


        Data data = new Data();
        data.init_extra(em);


        response.redirect("/muro");
        return null;
    }

    public static Void borrar_datos(Request request, Response response, EntityManager em) {


        Data data = new Data();
        data.limpiar(em);


        response.redirect("/muro");
        return null;
    }



}
