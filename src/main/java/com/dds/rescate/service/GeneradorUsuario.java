package com.dds.rescate.service;

import com.dds.rescate.exception.UsuarioException;
import com.dds.rescate.model.Usuario;
import com.dds.rescate.util.validaciones.ComprobarCaracteresRepetidos;
import com.dds.rescate.util.validaciones.ComprobarSiPoseeMasDe8Caracteres;
import com.dds.rescate.util.validaciones.ValidacionContrasenia;
import com.dds.rescate.util.validaciones.ValidarTopPeoresContrasenias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneradorUsuario {

    private int cont = 0;

    private List<ValidacionContrasenia> validaciones = Arrays.asList(new ComprobarCaracteresRepetidos(),new ComprobarSiPoseeMasDe8Caracteres(),new ValidarTopPeoresContrasenias());

    private List<Usuario> usuarioRepository = new ArrayList<>();

    public List<Usuario> getUsuarioRepository() {
        return usuarioRepository;
    }

    public GeneradorUsuario() {
    }

    public void registrarUsuario(Usuario usuario){
        validarContrasenia(usuario.getUsername(),usuario.getPassword());
        getUsuarioRepository().add(usuario);
    }

    public String iniciarSesion(String username, String password) {
        String mensaje = null;
        if(cont==3) {
            throw new UsuarioException("Llego al Limite de intentos");
        }
        try {
            validarUsuario(username,password);
            cont = 0;

            mensaje = "Logeado!";
        }catch(UsuarioException e){
           mensaje = e.getMessage();
            cont++;
        }
        return mensaje;
    }

    private void validarContrasenia(String username, String password){
        this.validaciones.forEach(validacion -> validacion.validar(username, password));
    }

    private int validarUsuario(String username, String password) {
        List<Usuario> us= new ArrayList<>();
        int cont = 0;
        Usuario usuario = new Usuario(username,password);
        us =this.usuarioRepository.stream().filter(userRepo->validar(userRepo, usuario)).collect(Collectors.toList());
        cont++;

        if(us.size()==0) {
            throw new UsuarioException("Usuario Y contrasenia inexistente");
        }
        return cont;
    }

    private boolean validar(Usuario usuarioRepo,Usuario usuarioAValidar) {

        return usuarioRepo.getUsername().equals(usuarioAValidar.getUsername()) && usuarioRepo.getPassword().equals(usuarioAValidar.getPassword());
    }
}
