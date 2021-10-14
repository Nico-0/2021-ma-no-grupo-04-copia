package com.dds.rescate.model;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.TipoMascota;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Publicacion {

    public int ID;
    public UsuarioDuenio autor;
    public Asociacion asociacionAsignada;
    public LocalDateTime fechaCreacion;
    public EstadoPubli estadoPublicacion;
    private TipoMascota tipoMascota;

    //Cnstructor
    public Publicacion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota) {
        this.autor = autor;
        this.asociacionAsignada = asociacionAsignada;
        this.fechaCreacion = LocalDateTime.now();
        this.estadoPublicacion = EstadoPubli.PENDIENTE;
        this.tipoMascota = tipoMascota;
    }

    public String getIdString(){
        return Integer.toString(ID);
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getFechaString(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return getFechaCreacion().format(formatter);
    }

    public EstadoPubli getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPubli estadoPublicacion) {
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
    public String getTipoPublihbs(){
        return "no existe publicacion sin herencia";
    }
    public String getTipoPubli(){
        return "generica";
    }
    public String getAnimal(){
        return tipoMascota.toString();
    }

    //Metodos
    public Boolean isPublicada(){
        return this.estadoPublicacion == EstadoPubli.PUBLICADA;
    }

    public Boolean isPendiente(){
        return this.estadoPublicacion == EstadoPubli.PENDIENTE;
    }

    public Boolean isFinalizada(){
        return this.estadoPublicacion == EstadoPubli.FINALIZADA;
    }

    public void aceptarPublicacion(){
        this.estadoPublicacion = EstadoPubli.PUBLICADA;
    }

    public void darDeBaja(){
        this.estadoPublicacion = EstadoPubli.CANCELADA;
    }

    public void setDescripcion(String descripcion) {
    }

    public void setNombreRescatista(String nombre) {
    }

    public void setEstado(EstadoPubli estadoPubli) {
        this.estadoPublicacion = estadoPubli;
    }

    public void setContactos(List<Contacto> contactos) {
        //TODO asignar los contactos
    }

    public String getTipoMascotaString(){
        return tipoMascota.toString();
    }

    public String getContactoString(){
        return autor.getPerfil().getContactos().get(0).getContactoString();
    }

}



