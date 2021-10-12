package com.dds.rescate.service;

import com.dds.rescate.exception.PasswordException;
import com.dds.rescate.exception.UsuarioException;
import com.dds.rescate.model.Usuario;
import com.dds.rescate.util.validaciones.ComprobarCaracteresRepetidos;
import com.dds.rescate.util.validaciones.superarLongitudMinima;
import com.dds.rescate.util.validaciones.ValidacionContrasenia;
import com.dds.rescate.util.validaciones.ValidarTopPeoresContrasenias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneradorUsuario {

    private int cont = 0;

    private final List<ValidacionContrasenia> validaciones = Arrays.asList(new ComprobarCaracteresRepetidos(),new superarLongitudMinima(),new ValidarTopPeoresContrasenias());

    private List<Usuario> usuarioRepository = new ArrayList<>();

    private static GeneradorUsuario instance = null;

    public static GeneradorUsuario getInstance() {
        if(instance == null) {
            instance = new GeneradorUsuario();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository;
    }

    public GeneradorUsuario() {
    }

    public void registrarUsuario(Usuario usuario){
        validarContrasenia(usuario.getUsername(),usuario.getPassword());
        getUsuarios().add(usuario);
    }

    private void validarContrasenia(String username, String password){
        this.validaciones.forEach(validacion -> validacion.validar(username, password));
    }

    public Boolean checkUsuario(String username, String password){
        Boolean existe = false;
        Usuario usuarioBuscado = obtenerUsuario(username);
        if(usuarioBuscado.getPassword().equals(password)){
            existe = true;
        }

        return existe;
    }

    public Usuario obtenerUsuario(String username){
        List<Usuario> usuarios;
        usuarios = this.getUsuarios().stream().filter(user->(username.equals(user.getUsername()))).collect(Collectors.toList());
        if(usuarios.size() == 0){
            throw new RuntimeException("No existe el usuario");
        }

        return usuarios.get(0);
    }

    public void agregarUsuario(Usuario nuevo){
        this.usuarioRepository.add(nuevo);
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

    private int validarUsuario(String username, String password) {
        List<Usuario> us= new ArrayList<>();
        int cont = 0;
        Usuario usuario = new Usuario(username,password);
        us =this.getUsuarios().stream().filter(userRepo->validar(userRepo, usuario)).collect(Collectors.toList());
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
