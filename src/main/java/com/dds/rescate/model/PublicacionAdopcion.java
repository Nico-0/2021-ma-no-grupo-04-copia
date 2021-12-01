package com.dds.rescate.model;

import com.dds.rescate.model.Enum.EstadoPubli;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PublicacionAdopcion extends Publicacion {

    @OneToOne //TODO es oneToOne??
    public Mascota mascota;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_adopcion")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Respuesta> preguntas;

    @OneToOne
    private UsuarioDuenio nuevo_duenio;
    private Date fecha_adopcion;

    //Constructor
    public PublicacionAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada,
                               Mascota mascotaPublicacion, List<Respuesta> preguntas) {
        super(autor, asociacionAsignada, mascotaPublicacion.getTipoMascota());
        asociacionAsignada.validarPreguntasAsociacion(preguntas);
        //TODO validar respuestas a las preguntas
        this.mascota = mascotaPublicacion;
        this.preguntas = preguntas;
        verificarDuenioMascota(autor, mascotaPublicacion);
        verificarMascotaPublicada(mascotaPublicacion);
        mascotaPublicacion.publicada = true;
    }
    private PublicacionAdopcion(){
    }

    //Getters y Setters

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

    public List<Respuesta> getPreguntas(){
        return preguntas;
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

    @Override
    public UsuarioDuenio getInteresado(){
        return nuevo_duenio;
    }


    public void reservarMascota(UsuarioDuenio nuevo_duenio){
        pendienteConfirmacion = true;
        this.nuevo_duenio = nuevo_duenio;
        this.fecha_adopcion = new Date();
    }

    @Override
    public void cancelarReserva(){
        pendienteConfirmacion = false;
        this.nuevo_duenio = null;
        this.fecha_adopcion = null;
    }

    @Override
    public void adoptarMascota(UsuarioDuenio nuevo_duenio){
        pendienteConfirmacion = false;
        setEstadoPublicacion(EstadoPubli.FINALIZADA);
        //this.nuevo_duenio = nuevo_duenio;
        this.fecha_adopcion = new Date();
        nuevo_duenio.getMascotas().add(mascota);
        autor.getMascotas().remove(mascota);
        getMascota().publicada = false;
    }

    public String getAdoptanteString(){
        return nuevo_duenio.getNombreCompleto();
    }
    public String getAdoptanteUserString(){
        return nuevo_duenio.getUsername();
    }
    public String getFechaAdopcion(){
        return fecha_adopcion.toString();
    }

    private void verificarDuenioMascota(UsuarioDuenio autor, Mascota mascota){
        if(!autor.getMascotas().contains(mascota)){
            new RuntimeException("Solo el duenio de la mascota "+mascota.getNombre()+" puede publicarla en adopcion");
        }
    }

}
