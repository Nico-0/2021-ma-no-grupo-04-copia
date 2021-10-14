package com.dds.rescate.controllers;

import com.dds.rescate.model.DatosPersonales;
import com.dds.rescate.model.Usuario;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Perfil {
    public static ModelAndView perfil(Request request, Response response){
        HashMap<String, Object> viewProfile = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewProfile.put("username", username);
        viewProfile.put("tipoUsuario", tipoUsuario);

        viewProfile.put("Perfil", "Datos Personales");
        GeneradorUsuario repoUsuarios = GeneradorUsuario.getInstance();
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        //System.out.print(user);

        DatosPersonales datosUser = user.getPerfil();
        viewProfile.put("Nombre", datosUser.getNombre());
        viewProfile.put("Apellido", datosUser.getApellido());

        //Date date = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd MMMM yyyy");
        String fechaNac = dt1.format(datosUser.getFechaNacimiento());
        //System.out.print(fechaNac);

        viewProfile.put("FechaNacimiento", fechaNac);
        //viewProfile.put("FechaNacimiento", datosUser.getFechaNacimiento());
        viewProfile.put("NumeroDoc", datosUser.getNroDoc());

        return new ModelAndView(viewProfile, "perfil.hbs");
    }
}
