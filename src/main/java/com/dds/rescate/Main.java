package com.dds.rescate;

import com.dds.rescate.model.Usuario;
import com.dds.rescate.service.GeneradorUsuario;

public class Main {
    public static void main(String[] args) {
        Usuario edu = new Usuario("eduRios","edu7298765");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        generadorUsuario.registrarUsuario(edu);
    }
}
