package com.dds.rescate.model;

import java.util.List;

public class PublicacionPerdida extends Publicacion {

    public Foto foto;
    public String descripcion;
    public String nombreRescatista;
    public List<Contacto> contacto;
    public Boolean estaPerdido;


    //Constructor
    public PublicacionPerdida(UsuarioDuenio autor, Asociacion asociacionAsignada, Estado estadoPublicacion,
                              Foto fotoPubli, String descripcion, String nombreRescatista,
                              List<Contacto> contacto) {
        super(autor, asociacionAsignada, estadoPublicacion);
        this.foto = fotoPubli;
        this.descripcion = descripcion;
        this.nombreRescatista = nombreRescatista;
        this.contacto = contacto;
        this.estaPerdido = true;
    }

    //Getters y Setters
    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreRescatista() {
        return nombreRescatista;
    }

    public void setNombreRescatista(String nombreRescatista) {
        this.nombreRescatista = nombreRescatista;
    }

    public List<Contacto> getContacto() {
        return contacto;
    }

    public void setContacto(List<Contacto> contacto) {
        this.contacto = contacto;
    }

    public Boolean getEstaPerdido() {
        return estaPerdido;
    }

    public void setEstaPerdido(Boolean estaPerdido) {
        this.estaPerdido = estaPerdido;
    }

    //Metodos
    public void aceptarPublicacion(){
        //TODO
    }
}
