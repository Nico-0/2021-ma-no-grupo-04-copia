package com.dds.rescate.model;

public class Formulario {
    public DatosPersonales datosPersonales;
    public Foto foto;
    public String descripcion;
    public Ubicacion ubicacionEncontrada;

    public Formulario(DatosPersonales datosPersonales, Foto foto, String descripcion, Ubicacion ubicacionEncontrada){
        this.datosPersonales = datosPersonales;
        this.foto = foto;
        this.descripcion = descripcion;
        this.ubicacionEncontrada = ubicacionEncontrada;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public Foto getFoto() {
        return foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Ubicacion getUbicacionEncontrada() {
        return ubicacionEncontrada;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUbicacionEncontrada(Ubicacion ubicacionEncontrada) {
        this.ubicacionEncontrada = ubicacionEncontrada;
    }
}
