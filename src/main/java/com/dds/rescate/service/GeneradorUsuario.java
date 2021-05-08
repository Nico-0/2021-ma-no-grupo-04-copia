package com.dds.rescate.service;

import com.dds.rescate.model.Usuario;
import com.dds.rescate.util.validaciones.ComprobarCaracteresRepetidos;
import com.dds.rescate.util.validaciones.ComprobarSiPoseeMasDe8Caracteres;
import com.dds.rescate.util.validaciones.ValidacionContrasenia;
import com.dds.rescate.util.validaciones.ValidarTopPeoresContrasenias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneradorUsuario {

    private List<ValidacionContrasenia> validaciones = Arrays.asList(new ComprobarCaracteresRepetidos(),new ComprobarSiPoseeMasDe8Caracteres(),new ValidarTopPeoresContrasenias());

    public GeneradorUsuario() {
    }

    public void registrarUsuario(Usuario usuario){
        validarContrasenia(usuario.getUsername(),usuario.getPassword());
    }

    private void validarContrasenia(String username, String password){
        this.validaciones.forEach(validacion -> validacion.validar(username, password));
    }
}
