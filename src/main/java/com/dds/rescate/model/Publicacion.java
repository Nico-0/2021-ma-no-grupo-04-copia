package com.dds.rescate.model;
import java.util.ArrayList;
import java.util.List;
public class Publicacion {
    public Foto foto;
    private String descripcion;
    private String nombreRescatista;
    private List<Contacto> contactos = new ArrayList<>();
    private Boolean estaPerdido;
    private Estado estado;

    //COnstructor

    public Publicacion() {
    }

    public Publicacion(Foto foto, String descripcion, String nombreRescatista, List<Contacto> contacto, Boolean estaPerdido){
        this.foto = foto;
        this.descripcion = descripcion;
        this.nombreRescatista = nombreRescatista;
        this.contactos = contacto;
        this.estaPerdido = estaPerdido;
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
    public List<Contacto> getContactos() {
        return contactos;
    }
    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
    public Boolean getEstaPerdido() {
        return estaPerdido;
    }
    public void setEstaPerdido(Boolean estaPerdido) {
        this.estaPerdido = estaPerdido;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
