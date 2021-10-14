package com.dds.rescate.model;

import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.util.Global;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicacionAdopcion extends Publicacion {

    public List<Respuesta> respuestasPreguntasAsociacion;
    public List<Respuesta> respuestasPreguntasGenerales;
    public Mascota mascota;
    private List<CaracteristicaMascota> requerimientos;
    private UsuarioDuenio nuevo_duenio;
    private Date fecha_adopcion;

    //Constructor
    public PublicacionAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada,
                               List<Respuesta> respuestasPreguntasAsociacion,
                               List<Respuesta> respuestasPreguntasGenerales,
                               Mascota mascotaPublicacion, List<CaracteristicaMascota> reqGenerales) {
        super(autor, asociacionAsignada, mascotaPublicacion.getTipoMascota());
        Global.getInstance().validarRequerimientosGenerales(reqGenerales);
        this.respuestasPreguntasAsociacion = respuestasPreguntasAsociacion;
        this.respuestasPreguntasGenerales = respuestasPreguntasGenerales;
        this.mascota = mascotaPublicacion;
        this.requerimientos = reqGenerales;
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

    public List<CaracteristicaMascota> getRequerimientos(){
        return requerimientos;
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
        return mascota.getFotoString();
    }


    public void adoptarMascota(UsuarioDuenio nuevo_duenio){
        setEstadoPublicacion(EstadoPubli.FINALIZADA);
        this.nuevo_duenio = nuevo_duenio;
        this.fecha_adopcion = new Date();
        nuevo_duenio.getMascotas().add(mascota);
        autor.getMascotas().remove(mascota);
    }

    public String getAdoptanteString(){
        return nuevo_duenio.getNombre();
    }
    public String getFechaAdopcion(){
        return fecha_adopcion.toString();
    }

}
