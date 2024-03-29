package com.dds.rescate.controllers;

import com.dds.rescate.exception.PasswordException;
import com.dds.rescate.model.*;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.RepoAsociacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class LoginController {

    public static String verificarLogin(Request request){
        String tipoUsuario = request.cookie("tipoUsuario");
        if(tipoUsuario == null)
            tipoUsuario = "null";
        if(!tipoUsuario.equals("comun") && !tipoUsuario.equals("voluntario") && !tipoUsuario.equals("administrador"))
            tipoUsuario = "null";
        return tipoUsuario;
    }


    public static Void login(Request request, Response response, EntityManager em){
        if(request.queryParams("user_name") != null && request.queryParams("password") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");

            GeneradorUsuario repositorio = new GeneradorUsuario(em);
            try {
                if (repositorio.checkUsuario(username, password)) {

                    Usuario usuario = repositorio.obtenerUsuario(username);
                    response.cookie("tipoUsuario", usuario.getTipo());
                    response.cookie("nombrePersona", usuario.getNombreCompleto());
                    //TO DO guardar asociacion para voluntario y admin
                    //al menos admin no lo maneja por cookie
                    response.cookie("username", username);

                    response.redirect("/");
                }
            }catch (RuntimeException no_existe_usuario){
                response.redirect("/error/usuario");
            }
            //throw new RuntimeException("Contraseña incorrecta");
            response.redirect("/error/contrasenia");
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
        HashMap<String, Object> viewModel = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewModel.put("username", username);
        viewModel.put("tipoUsuario", tipoUsuario);

        return new ModelAndView(viewModel, "registro.hbs");
    }

    //------------------------------no hacemos el alta de usuarios----------------------------------------------------------------------------------------

    //TO DO verificar que ningun campo se complete con espacios
    public static Void comun(Request request, Response response, EntityManager em) {
        if(request.queryParams("user_name") != null && request.queryParams("password") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");

            //TO DO crear metodo que no pida datos personales y cargar datos personales en /perfil

            UsuarioDuenio nuevoUsuario = new UsuarioDuenio(username, password, null);
            GeneradorUsuario repo = new GeneradorUsuario(em);
            repo.registrarUsuario(nuevoUsuario);

            //login automatico para no ser redireccionado a "/" por spark.before por no tener la cookie
            //cookie en "/" para que no la setee en /registro
            response.cookie("/","tipoUsuario", "comun", -1, false);
            response.cookie("/","username", username, -1, false);

            response.redirect("/registro/sucess");
        }

        return null;
    }

    public static Void voluntario(Request request, Response response, EntityManager em) {
        if(request.queryParams("user_name") != null && request.queryParams("password") != null && request.queryParams("asociacion") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");
            String asociacion = request.queryParams("asociacion");

            RepoAsociacion repoAsociacion = new RepoAsociacion(em);
            Asociacion asociacion_objeto = repoAsociacion.getAsociacionByNombre(asociacion);

            //TO DO no dar asociacion null
            //TO DO verificar que la asociacion ya exista
            UsuarioVoluntario nuevoUsuario = new UsuarioVoluntario(username, password, asociacion_objeto);
            GeneradorUsuario repo = new GeneradorUsuario(em);
            repo.registrarUsuario(nuevoUsuario);

            //login automatico para no ser redireccionado a "/" por spark.before por no tener la cookie
            //cookie en "/" para que no la setee en /registro
            response.cookie("/","tipoUsuario", "voluntario", -1, false);
            response.cookie("/","username", username, -1, false);
            response.cookie("/","asociacion", asociacion, -1, false);

            response.redirect("/registro/sucess");
        }

        return null;
    }

    public static Void admin(Request request, Response response, EntityManager em) {
        if(request.queryParams("user_name") != null && request.queryParams("password") != null && request.queryParams("asociacion") != null) {
            String username = request.queryParams("user_name");
            String password = request.queryParams("password");
            String asociacion = request.queryParams("asociacion");

            RepoAsociacion repoAsociacion = new RepoAsociacion(em);
            Asociacion asociacion_objeto = repoAsociacion.getAsociacionByNombre(asociacion);

            //TO DO asociar organizacion al administrador
            //TO DO crear organizacion si no existe
            UsuarioAdministrador nuevoUsuario = new UsuarioAdministrador(username, password, asociacion_objeto);
            GeneradorUsuario repo = new GeneradorUsuario(em);
            repo.registrarUsuario(nuevoUsuario);

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
