package com.dds.rescate.controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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






}
