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

    public String descripcion;

    @ManyToMany
    //@JoinColumn(name = "FK_publicacion_perdida")
    //@LazyCollection(LazyCollectionOption.FALSE)
    public List<Contacto> contactos = new ArrayList<>();

    @Transient
    public Ubicacion ubicacionEncontrada;
    @Transient
    public String hogarTransito;

    @OneToOne //TODO es oneToOne??
    Mascota mascota_rescatada;

    @Enumerated(EnumType.STRING)
    public EstadoEncontrada estadoEncontrada;

    private Date fecha_recuperacion;

    //Constructor
    public PublicacionPerdida(UsuarioDuenio autor, Asociacion asociacionAsignada, Mascota mascota_rescatada,
                              EstadoEncontrada estadoEncontrada, String descripcion, Contacto contactoMinimo) {
        super(autor, asociacionAsignada, mascota_rescatada.getTipoMascota());
        this.mascota_rescatada = mascota_rescatada;
        //TODO asociacionAsignada debe ser la mas cercana a la ubicacionEncontrada
        this.descripcion = descripcion;
        this.contactos.add(contactoMinimo);
        this.estadoEncontrada = estadoEncontrada;
        //TODO se elige el contacto o se saca del usuario rescatista?
        verificarMascotaPublicada(mascota_rescatada);
        mascota_rescatada.publicada = true;
        autor.agregarMascota(mascota_rescatada);
    }
    private PublicacionPerdida(){}

    //Getters y Setters
    public String getFoto() {
        return mascota_rescatada.getFotos().get(0);
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
        return mascota_rescatada.getFotoString();
    }

    public String getFechaRecuperacion(){
        return fecha_recuperacion.toString();
    }

    public Mascota getMascota(){
        return mascota_rescatada;
    }

    public void recuperarMascota(UsuarioDuenio nuevo_duenio) {
        setEstadoPublicacion(EstadoPubli.FINALIZADA);
        this.fecha_recuperacion = new Date();
        nuevo_duenio.getMascotas().add(getMascota());
        autor.getMascotas().remove(getMascota());
        getMascota().publicada = false;
    }
}
