package com.dds.rescate.model;
import java.util.List;
public class Publicacion {
    public Foto foto;
    public String descripcion;
    public String nombreRescatista;
    public List<Contacto> contacto;
    public Boolean estaPerdido;
    //Estado estado;

    //COnstructor
    public Publicacion(Foto foto, String descripcion, String nombreRescatista, List<Contacto> contacto, Boolean estaPerdido){
        this.foto = foto;
        this.descripcion = descripcion;
        this.nombreRescatista = nombreRescatista;
        this.contacto = contacto;
        this.estaPerdido = estaPerdido;
    }
}
