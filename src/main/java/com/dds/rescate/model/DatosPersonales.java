package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosPersonales {

    public String nombre;
    public String apellido;
    public Date fechaNacimiento;
    public Integer nroDoc;
    public List<Contacto> contactos = new ArrayList<>();

    //Constructor
    public DatosPersonales(String nombre, String apellido, Date fechaNacimiento, Integer nroDoc, Contacto contactoMinimo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nroDoc = nroDoc;
        this.contactos.add(contactoMinimo);
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
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

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNroDoc(Integer nroDoc) {
        this.nroDoc = nroDoc;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}
