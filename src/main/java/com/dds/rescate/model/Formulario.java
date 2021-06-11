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
}
