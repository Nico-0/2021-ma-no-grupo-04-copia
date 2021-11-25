package com.dds.rescate.service;

import com.dds.rescate.exception.PasswordException;
import com.dds.rescate.exception.UsuarioException;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.Usuario;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.util.validaciones.ComprobarCaracteresRepetidos;
import com.dds.rescate.util.validaciones.superarLongitudMinima;
import com.dds.rescate.util.validaciones.ValidacionContrasenia;
import com.dds.rescate.util.validaciones.ValidarTopPeoresContrasenias;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneradorUsuario {

    public GeneradorUsuario() {
    }

    private final List<ValidacionContrasenia> validaciones = Arrays.asList(new ComprobarCaracteresRepetidos(),new superarLongitudMinima(),new ValidarTopPeoresContrasenias());

    private EntityManager entityManager;

    public GeneradorUsuario(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Usuario> getUsuarios() {
        return entityManager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    public List<UsuarioDuenio> getUsuariosDuenio() {
        return entityManager.createQuery("from UsuarioDuenio", UsuarioDuenio.class).getResultList();
    }

    public void registrarUsuario(Usuario usuario){
        validarContrasenia(usuario.getUsername(),usuario.getPassword());
        //getUsuarios().add(usuario); para base en variable
        entityManager.persist(usuario);
    }

    private void validarContrasenia(String username, String password){
        this.validaciones.forEach(validacion -> validacion.validar(username, password));
    }

    public Boolean checkUsuario(String username, String password){
        Boolean coincide = false;
        Usuario usuarioBuscado = obtenerUsuario(username);
        if(usuarioBuscado.getPassword().equals(password)){
            coincide = true;
        }

        return coincide;
    }

    public Usuario obtenerUsuario(String username){
        List<Usuario> usuarios;
        usuarios = getUsuarios().stream().filter(user->(username.equals(user.getUsername()))).collect(Collectors.toList());
        if(usuarios.size() == 0){
            throw new RuntimeException("No existe el usuario");
        }

        return usuarios.get(0);
    }


    public String getIdByUsername(String username){
        Usuario user = getUsuarios().stream().filter(u -> u.getUsername().equals(username)).findAny().orElse(null);

        if(user != null){
            return Integer.toString(user.getID());
        }
        return null;
    }

    public Usuario getUserByID(int id_user){
        //return usuarioRepository.stream().filter(u -> u.getID() == id_user).findAny().orElse(null);
        return entityManager.createQuery("from Usuario u where u.ID = ?1", Usuario.class)
                .setParameter(1, id_user)
                .getSingleResult();
    }











//------------------------------------------------------------------ sin usar

    private List<Usuario> usuarioRepository = new ArrayList<>();
    private static GeneradorUsuario instance = null;
    private static int ID_actual;
    public static GeneradorUsuario getInstance() {
        if(instance == null) {
            instance = new GeneradorUsuario();
            ID_actual = 0;
        }
        return instance;
    }
    public void agregarUsuario(Usuario nuevo){
        nuevo.setID(ID_actual);
        ID_actual++;
        this.usuarioRepository.add(nuevo);
    }

//----------------------------------------------------------------

    private int cont = 0;

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
