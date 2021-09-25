package com.dds.rescate.controllers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class HomeController {
    public static ModelAndView home(Request request, Response response){
        String tipoUsuario = request.cookie("tipoUsuario");
        if (tipoUsuario == null)
            tipoUsuario = "null";

        if(tipoUsuario.equals("comun") || tipoUsuario.equals("voluntario") || tipoUsuario.equals("administrador")){
            HashMap<String, Object> viewModel = new HashMap<>();
            viewModel.put("tipoUsuario", tipoUsuario);
            String username = request.cookie("username");
            viewModel.put("username", username);
            String nombrePersona = request.cookie("nombrePersona");
            viewModel.put("nombrePersona", nombrePersona);
            return new ModelAndView(viewModel, "home.hbs");
        }
        else
        {
            return new ModelAndView(null, "homeSinLogin.hbs");
        }




    }

    public static Void login(Request request, Response response){
        if(request.queryParams("user_name") != null && request.queryParams("password") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");

            //TODO buscar nombre de la persona y tipo de usuario en el repositorio

            response.cookie("tipoUsuario", "comun");
            response.cookie("nombrePersona", password);
            response.cookie("username", username);

            response.redirect("/");
        }

        return null;
    }

    public static Void logout(Request request, Response response){

        response.removeCookie("tipoUsuario");
        response.removeCookie("nombrePersona");
        response.removeCookie("username");

        response.redirect("/");

        return null;
    }






}
