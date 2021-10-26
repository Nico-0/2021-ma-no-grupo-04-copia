package com.dds.rescate.model;

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
    @OneToMany
    @JoinColumn(name = "FK_datosPersonales")
    public List<Contacto> contactos = new ArrayList<>();

    public DatosPersonales(String nombre, String apellido, Date fechaNacimiento, Integer nroDoc, Contacto contactoMinimo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nroDoc = nroDoc;
        this.contactos.add(contactoMinimo);
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
