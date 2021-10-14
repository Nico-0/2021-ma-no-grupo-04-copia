package com.dds.rescate.controllers;

import com.dds.rescate.model.Mascota;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.GeneradorUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class MisMascotas {
    public static ModelAndView show(Request request, Response response){
        HashMap<String, Object> viewMascotas = new HashMap<>();
        String username = request.cookie("username");
        String tipoUsuario = request.cookie("tipoUsuario");
        viewMascotas.put("username", username);
        viewMascotas.put("tipoUsuario", tipoUsuario);

        GeneradorUsuario repoUsuarios = GeneradorUsuario.getInstance();
        UsuarioDuenio user = (UsuarioDuenio) repoUsuarios.obtenerUsuario(username);

        //System.out.print(user);

        //Mascotas de usuario
        List<Mascota> mascotas = user.getMascotas();
        viewMascotas.put("Mascotas", mascotas);
        /*for(Mascota  masc : mascotas){
            System.out.println(masc.toString());
        }*/

        return new ModelAndView(viewMascotas, "misMascotas.hbs");
    }
}
