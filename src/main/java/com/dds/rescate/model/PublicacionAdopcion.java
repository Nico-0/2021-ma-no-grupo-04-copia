package com.dds.rescate.model;

import com.dds.rescate.model.Enum.EstadoPubli;

import java.util.List;

public class PublicacionAdopcion extends Publicacion {

    public List<Respuesta> respuestasPreguntasAsociacion;
    public List<Respuesta> respuestasPreguntasGenerales;
    public Mascota mascota;
    public EstadoPubli estadoPublicacion;

    //Constructor
    public PublicacionAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada,
                               List<Respuesta> respuestasPreguntasAsociacion,
                               List<Respuesta> respuestasPreguntasGenerales,
                               Mascota mascotaPublicacion) {
        super(autor, asociacionAsignada, mascotaPublicacion.getTipoMascota());
        this.respuestasPreguntasAsociacion = respuestasPreguntasAsociacion;
        this.respuestasPreguntasGenerales = respuestasPreguntasGenerales;
        this.mascota = mascotaPublicacion;

    }

    //Getters y Setters
    public List<Respuesta> getRespuestasPreguntasAsociacion() {
        return respuestasPreguntasAsociacion;
    }

    public void setRespuestasPreguntasAsociacion(List<Respuesta> respuestasPreguntasAsociacion) {
        this.respuestasPreguntasAsociacion = respuestasPreguntasAsociacion;
    }

    public List<Respuesta> getRespuestasPreguntasGenerales() {
        return respuestasPreguntasGenerales;
    }

    public void setRespuestasPreguntasGenerales(List<Respuesta> respuestasPreguntasGenerales) {
        this.respuestasPreguntasGenerales = respuestasPreguntasGenerales;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public EstadoPubli getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPubli estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    //Metodos
    public void contactarDuenio(UsuarioDuenio adoptante){
        //TODO
    }

    public void confirmarAdopcion(UsuarioDuenio adoptante){
        //TODO
    }

    @Override
    public String getTipoPublihbs(){
        return "publiAdopcion.hbs";
    }

    @Override
    public String getTipoPubli(){
        return "adopcion";
    }

    public String getNombreMascota(){
        return mascota.getNombre();
    }

    public String getApodoMascota(){
        return mascota.getApodo();
    }

    public String getFotoString(){
        return mascota.getFotos().get(0).nombreFoto;
    }



}
