package com.dds.rescate.service;

import com.dds.rescate.exception.PasswordException;
import com.dds.rescate.exception.UsuarioException;
import com.dds.rescate.model.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneradorUsuarioTest {

    GeneradorUsuario generadorUsuario = new GeneradorUsuario();

    @Before
    public void init(){
        Usuario fulano = new Usuario("fulano","fulano7298756");
        generadorUsuario.registrarUsuarioTest(fulano);
    }
    @Test
    public void registrarUsuarioOk(){
        Usuario fulano = new Usuario("fulano","fulano7298765");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        generadorUsuario.registrarUsuarioTest(fulano);
        assertEquals(1,generadorUsuario.getUsuariosTest().size());
    }
    
    @Test(expected = PasswordException.class)
    public void registrarUsuarioPasswordException(){
        Usuario fulano = new Usuario("fulano","fulano7298755");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        generadorUsuario.registrarUsuarioTest(fulano);
        System.out.println(generadorUsuario.getUsuariosTest().size());
    }

    @Test
    public void registrarUsuarioErrorPasswordRepetidos(){
        String mensajeError ="";
        Usuario fulano = new Usuario("fulano","fulano7298755");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        try {
            generadorUsuario.registrarUsuarioTest(fulano);
        }catch(PasswordException e){
            mensajeError = e.getMessage();
        }
        assertEquals("Hay caracteres repetidos!",mensajeError);
    }

    @Test
    public void registrarUsuarioErrorPasswordMenosOchoCaracteres(){
        String mensajeError ="";
        Usuario fulano = new Usuario("fulano","fula");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        try {
            generadorUsuario.registrarUsuarioTest(fulano);
        }catch(PasswordException e){
            mensajeError = e.getMessage();
        }
        assertEquals("Inutilizable: no cumple con el minimo de caracteres!!",mensajeError);
    }

    @Test
    public void registrarUsuarioErrorTop10kTopPeoresPassword(){
        String mensajeError ="";
        Usuario fulano = new Usuario("fulano","superman");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        try {
            generadorUsuario.registrarUsuarioTest(fulano);
        }catch(PasswordException e){
            mensajeError = e.getMessage();
        }
        assertEquals("La Password es debil (presente en top 10k)",mensajeError);
    }

    @Test
    public void iniciarSesionOk(){

        String respuesta = generadorUsuario.iniciarSesion("fulano","fulano7298756");
        assertEquals("Logeado!",respuesta);
    }

    @Test
    public void iniciarSesionErrorUsuarioInexistente(){

        String mensaje = generadorUsuario.iniciarSesion("fulano","fulano7298766");
        assertEquals("Usuario Y contrasenia inexistente",mensaje);
    }

    @Test(expected = UsuarioException.class)
    public void iniciarSesionErrorUsuarioException(){

        generadorUsuario.iniciarSesion("fulano","fulano7298766");
        generadorUsuario.iniciarSesion("fulano","fulano7298766");
        generadorUsuario.iniciarSesion("fulano","fulano7298766");
        generadorUsuario.iniciarSesion("fulano","fulano7298766");

    }

    @Test
    public void iniciarSesionErrorsSuperaLos3Intentos(){
        String mensaje = null;
        try{
            generadorUsuario.iniciarSesion("fulano","fulano7298775");
            generadorUsuario.iniciarSesion("fulano","fulano7298775");
            generadorUsuario.iniciarSesion("fulano","fulano7298775");
            generadorUsuario.iniciarSesion("fulano","fulano7298775");
        }catch(UsuarioException e){
            mensaje = e.getMessage();
        }

        assertEquals("Llego al Limite de intentos",mensaje);
    }
}
