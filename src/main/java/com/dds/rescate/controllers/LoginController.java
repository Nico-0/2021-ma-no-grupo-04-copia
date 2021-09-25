package com.dds.rescate.controllers;

import com.dds.rescate.exception.PasswordException;
import com.dds.rescate.model.Usuario;
import com.dds.rescate.model.UsuarioAdministrador;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.model.UsuarioVoluntario;
import com.dds.rescate.service.GeneradorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public static String verificarLogin(Request request){
        String tipoUsuario = request.cookie("tipoUsuario");
        if(tipoUsuario == null)
            tipoUsuario = "null";
        if(!tipoUsuario.equals("comun") && !tipoUsuario.equals("voluntario") && !tipoUsuario.equals("administrador"))
            tipoUsuario = "null";
        return tipoUsuario;
    }


    public static Void login(Request request, Response response){
        if(request.queryParams("user_name") != null && request.queryParams("password") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");

            GeneradorUsuario repositorio = GeneradorUsuario.getInstance();
            if(repositorio.checkUsuario(username,password)) {

                Usuario usuario = repositorio.obtenerUsuario(username);
                response.cookie("tipoUsuario", usuario.getTipo());
                //response.cookie("nombrePersona", password); //TODO buscar nombre en datos personales si es que es usuario comun
                //TODO guardar asociacion para voluntario y admin
                response.cookie("username", username);

                response.redirect("/");
            }
        }

        return null;
    }

    public static Void logout(Request request, Response response){

        response.removeCookie("tipoUsuario");
        response.removeCookie("nombrePersona");
        response.removeCookie("username");
        response.removeCookie("asociacion");

        response.redirect("/");

        return null;
    }

    public static ModelAndView sucess(Request request, Response response){

        return new ModelAndView(null, "registro.hbs");
    }

    //TODO verificar que ningun campo se complete con espacios
    public static Void comun(Request request, Response response) {
        if(request.queryParams("user_name") != null && request.queryParams("password") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");

            //TODO crear metodo que no pida datos personales y cargar datos personales en /perfil

            UsuarioDuenio nuevoUsuario = new UsuarioDuenio(username, password, null);
            GeneradorUsuario.getInstance().registrarUsuario(nuevoUsuario);

            //login automatico para no ser redireccionado a "/" por spark.before por no tener la cookie
            //cookie en "/" para que no la setee en /registro
            response.cookie("/","tipoUsuario", "comun", -1, false);
            response.cookie("/","username", username, -1, false);

            response.redirect("/registro/sucess");
        }

        return null;
    }

    public static Void voluntario(Request request, Response response) {
        if(request.queryParams("user_name") != null && request.queryParams("password") != null && request.queryParams("asociacion") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");
            String asociacion = request.queryParams("asociacion");

            //TODO no dar asociacion null
            UsuarioVoluntario nuevoUsuario = new UsuarioVoluntario(username, password, null);
            GeneradorUsuario.getInstance().registrarUsuario(nuevoUsuario);

            //login automatico para no ser redireccionado a "/" por spark.before por no tener la cookie
            //cookie en "/" para que no la setee en /registro
            response.cookie("/","tipoUsuario", "voluntario", -1, false);
            response.cookie("/","username", username, -1, false);
            response.cookie("/","asociacion", asociacion, -1, false);

            response.redirect("/registro/sucess");
        }

        return null;
    }

    public static Void admin(Request request, Response response) {
        if(request.queryParams("user_name") != null && request.queryParams("password") != null && request.queryParams("asociacion") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");
            String asociacion = request.queryParams("asociacion");

            //TODO asociar organizacion al administrador
            UsuarioAdministrador nuevoUsuario = new UsuarioAdministrador(username, password);
            GeneradorUsuario.getInstance().registrarUsuario(nuevoUsuario);

            //login automatico para no ser redireccionado a "/" por spark.before por no tener la cookie
            //cookie en "/" para que no la setee en /registro
            response.cookie("/","tipoUsuario", "administrador", -1, false);
            response.cookie("/","username", username, -1, false);
            response.cookie("/","asociacion", asociacion, -1, false);

            response.redirect("/registro/sucess");
        }

        return null;
    }
}
