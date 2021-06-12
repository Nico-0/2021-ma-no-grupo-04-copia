package com.dds.rescate.model;

public class Formulario {
    DatosPersonales datosPersonales;
    Foto foto;
    String descripcion;
    Ubicacion ubicacionEncontrada;

    public Formulario(DatosPersonales datosPersonales, Foto foto, String descripcion, Ubicacion ubicacionEncontrada){
        this.datosPersonales = datosPersonales;
        this.foto = foto;
        this.descripcion = descripcion;
        this.ubicacionEncontrada = ubicacionEncontrada;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }
    public void setDatosPersonales(DatosPersonales datos) {
        this.datosPersonales = datos;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
