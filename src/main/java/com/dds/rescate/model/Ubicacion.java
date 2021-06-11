package com.dds.rescate.model;

public class Ubicacion {
    String direccion;
    Long latitud;
    Long longitud;

    public Ubicacion(String direccion, Long latitud, Long longitud){
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
