package com.dds.rescate.model;
import java.time.LocalDateTime;
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

    //Metodos
    public void darDeBaja(){
        //TODO PAra todos las clases de las que hereda
    }
}



