package com.dds.rescate.model;

import java.util.List;

public class PublicacionAdopcion extends Publicacion {

    public List<Respuesta> respuestasPreguntasAsociacion;
    public List<Respuesta> respuestasPreguntasGenerales;
    public Mascota mascota;
    public Estado estadoPublicacion;

    //Constructor
    public PublicacionAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada,
                               List<Respuesta> respuestasPreguntasAsociacion,
                               List<Respuesta> respuestasPreguntasGenerales,
                               Mascota mascotaPublicacion, Estado estadoPublicacion) {
        super(autor, asociacionAsignada, estadoPublicacion);
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

    public Estado getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(Estado estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    //Metodos
    public void contactarDuenio(UsuarioDuenio adoptante){
        //TODO
    }

    public void confirmarAdopcion(UsuarioDuenio adoptante){
        //TODO
    }
}
