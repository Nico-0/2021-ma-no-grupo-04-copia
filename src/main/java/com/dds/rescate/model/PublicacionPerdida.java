package com.dds.rescate.model;

import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.TipoMascota;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PublicacionPerdida extends Publicacion {

    public String foto;
    public String descripcion;

    @ManyToMany
    //@JoinColumn(name = "FK_publicacion_perdida")
    //@LazyCollection(LazyCollectionOption.FALSE)
    public List<Contacto> contactos = new ArrayList<>();

    @Transient
    public Ubicacion ubicacionEncontrada;
    @Transient
    public String hogarTransito;

    @Enumerated(EnumType.STRING)
    public EstadoEncontrada estadoEncontrada;

    private Date fecha_recuperacion;

    //Constructor
    public PublicacionPerdida(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota,
                              String fotoPubli, EstadoEncontrada estadoEncontrada, String descripcion, Contacto contactoMinimo) {
        super(autor, asociacionAsignada, tipoMascota);
        //TODO asociacionAsignada debe ser la mas cercana a la ubicacionEncontrada
        this.foto = fotoPubli;
        this.descripcion = descripcion;
        this.contactos.add(contactoMinimo);
        this.estadoEncontrada = estadoEncontrada;
        //TODO se crea la nueva mascota en el sistema al adoptarse o aca?
        //TODO se elige el contacto o se saca del usuario rescatista?
    }
    private PublicacionPerdida(){}

    //Getters y Setters
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreRescatista() {
        return this.autor.getPerfil().getNombre();
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void addContacto(Contacto contacto) {
        this.contactos.add(contacto);
    }

    @Override
    public String getTipoPublihbs(){
        return "publiPerdida.hbs";
    }

    @Override
    public String getTipoPubli(){
        return "perdida";
    }

    public String getEstadoMascotaString(){
        return estadoEncontrada.toString();
    }

    public String getFotoString(){
        return foto;
    }

    public String getFechaRecuperacion(){
        return fecha_recuperacion.toString();
    }

    public void recuperarMascota() {
        setEstadoPublicacion(EstadoPubli.FINALIZADA);

        this.fecha_recuperacion = new Date();

        //TODO pedirle al usuario que de de alta la nueva mascota
    }
}
