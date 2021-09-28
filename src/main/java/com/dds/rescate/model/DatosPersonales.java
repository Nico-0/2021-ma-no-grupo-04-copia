package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosPersonales {
    public String nombre;
    public String apellido;
    public Date fechaNacimiento;
    public Integer nroDoc;
    public Ubicacion direccion;
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

    public List<Contacto> getDatosContacto() {
        return this.contactos;
    }
}
