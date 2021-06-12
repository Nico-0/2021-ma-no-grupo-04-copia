package com.dds.rescate.model;

import com.dds.rescate.service.PublicacionService;

public class Voluntario extends Usuario{

    public Voluntario(String username, String password) {
        super(username, password);
    }

    public void aceptarPublicaciones() {
        PublicacionService.getInstance().aceptarPublicaciones();
    }
}
