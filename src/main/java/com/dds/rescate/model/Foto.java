package com.dds.rescate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Foto {
    @Id
    @GeneratedValue
    private Long id;

    public String nombreFoto;

    public Foto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public void normalizar(){
        //Todo
    }
}
