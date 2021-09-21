package com.dds.rescate.model;
import java.time.LocalDateTime;
import java.util.List;

public class Publicacion {

    public UsuarioDuenio autor;
    public Asociacion asociacionAsignada;
    public LocalDateTime fechaCreacion;
    public Estado estadoPublicacion;

    //Cnstructor
    public Publicacion(UsuarioDuenio autor, Asociacion asociacionAsignada, Estado estado) {
        this.autor = autor;
        this.asociacionAsignada = asociacionAsignada;
        this.fechaCreacion = LocalDateTime.now();
        this.estadoPublicacion = estado;
    }

    public UsuarioDuenio getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDuenio autor) {
        this.autor = autor;
    }

    public Asociacion getAsociacionAsignada() {
        return asociacionAsignada;
    }

    public void setAsociacionAsignada(Asociacion asociacionAsignada) {
        this.asociacionAsignada = asociacionAsignada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estado getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(Estado estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public String getAutorString(){
        return this.autor.getNombre();
    }
    public String getAsociacionString(){
        return this.asociacionAsignada.getNombre();
    }
    public String getEstadoString(){
        return this.estadoPublicacion.toString();
    }

    //Metodos
    public void darDeBaja(){
        //TODO PAra todos las clases de las que hereda
    }

    public void setDescripcion(String descripcion) {
    }

    public void setNombreRescatista(String nombre) {
    }

    public void setEstado(Estado estado) {
        this.estadoPublicacion = estado;
    }

    public void setContactos(List<Contacto> contactos) {
        //TODO asignar los contactos
    }
}



