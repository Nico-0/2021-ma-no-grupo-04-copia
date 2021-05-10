package com.dds.rescate.service;

import com.dds.rescate.model.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneradorUsuarioTest {
    @Test
    public void registrarUsuarioOk(){
        Usuario fulano = new Usuario("fulano","fulano7298765");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        generadorUsuario.registrarUsuario(fulano);
        assertEquals(1,generadorUsuario.getUsuarioRepository().size());
    }

    @Test
    public void iniciarSesionOk(){
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        generadorUsuario.iniciarSesion("fulano","fulano7298765");
        //assertEquals(1,generadorUsuario.getUsuarioRepository());
    }
}
