package com.dds.rescate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DatosPersonales {
    @Id
    @GeneratedValue
    private int id;
    public String nombre;
    public String apellido;
    public Date fechaNacimiento;
    public Integer nroDoc;
    @Transient
    public Ubicacion direccion;
    @ManyToMany
    //@JoinColumn(name = "FK_datosPersonales") mejor que datos personales guarde la referencia al contacto (tabla adicional)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Contacto> contactos = new ArrayList<>();

    public DatosPersonales(String nombre, String apellido, Date fechaNacimiento, Integer nroDoc, Contacto contactoMinimo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nroDoc = nroDoc;
        this.contactos.add(contactoMinimo);
    }
    private DatosPersonales(){

    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Integer getNroDoc() {
        return nroDoc;
    }

    public Ubicacion getDireccion() {
        return direccion;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

}
