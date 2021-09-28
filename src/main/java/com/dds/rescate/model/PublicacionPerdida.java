package com.dds.rescate.model;

import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.TipoMascota;

import java.util.ArrayList;
import java.util.List;

public class PublicacionPerdida extends Publicacion {

    public Foto foto;
    public String descripcion;
    //public String nombreRescatista; seria el autor heredado
    public List<Contacto> contactos = new ArrayList<>();;
    public Ubicacion ubicacionEncontrada;
    public String hogarTransito;
    public EstadoEncontrada estadoEncontrada;

    //Constructor
    public PublicacionPerdida(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota,
                              String fotoPubli, EstadoEncontrada estadoEncontrada, String descripcion, Contacto contactoMinimo) {
        super(autor, asociacionAsignada, tipoMascota);
        //TODO asociacionAsignada debe ser la mas cercana a la ubicacionEncontrada
        this.foto = new Foto(fotoPubli);
        this.descripcion = descripcion;
        this.contactos.add(contactoMinimo);
        this.estadoEncontrada = estadoEncontrada;
        //TODO se crea la nueva mascota en el sistema al adoptarse o aca?
    }

    //Getters y Setters
    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
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
        return foto.nombreFoto;
    }

}
