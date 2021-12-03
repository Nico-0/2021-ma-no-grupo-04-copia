package com.dds.rescate.model;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.TipoMascota;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Publicacion {

    @Id
    @GeneratedValue
    public int ID;
    @ManyToOne
    public UsuarioDuenio autor;

    @ManyToOne
    @JoinColumn(name = "asoc_id")
    public Asociacion asociacionAsignada;

    public Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    public EstadoPubli estadoPublicacion;

    @Enumerated(EnumType.STRING)
    private TipoMascota tipoMascota;

    public Boolean pendienteConfirmacion = false;

    //Constructor
    public Publicacion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota) {
        this.autor = autor;
        this.asociacionAsignada = asociacionAsignada;
        this.fechaCreacion = new Date();
        this.estadoPublicacion = EstadoPubli.PENDIENTE;
        this.tipoMascota = tipoMascota;
    }

    public Publicacion(){}

    public int getId(){
        return ID;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaString(){
        /* Para LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return getFechaCreacion().format(formatter);*/
        return getFechaCreacion().toString();
    }

    public EstadoPubli getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(EstadoPubli estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public String getAutorString(){
        return this.autor.getNombreCompleto();
    }
    public String getUserString(){
        return this.autor.getUsername();
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

    public Boolean getPendienteConfirmacion(){return pendienteConfirmacion;}
    public UsuarioDuenio getInteresado(){
        return null;
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
        this.estadoPublicacion = EstadoPubli.FINALIZADA;
    }

    public void setEstado(EstadoPubli estadoPubli) {
        this.estadoPublicacion = estadoPubli;
    }

    public TipoMascota getTipoMascota(){
        return tipoMascota;
    }

    public String getTipoMascotaString(){
        return tipoMascota.toString();
    }

    public String getContactoString(){
        List<Contacto> contactos = autor.getPerfil().getContactos();
        if(contactos.size() == 0){
            throw new RuntimeException("No tiene contactos");
        }

        return contactos.get(0).getContactoString();
    }

    public void ordenarRecomendaciones() {
    }

    public void verificarMascotaPublicada(Mascota mascota){
        if(mascota.publicada){
            throw new RuntimeException("La mascota "+mascota.getNombre()+" ya se encuentra en otra publicacion");
        }
    }

    public void cancelarReserva(){
    }
    public void adoptarMascota(UsuarioDuenio nuevo_duenio) {
    }

    public Boolean getIsFinalizada(){
        return isFinalizada();
    }
    public Boolean getConcretada(){
        return false;
    }
}



