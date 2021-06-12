package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatosPersonales {
    private String nombre;
    private String apellido;
    private Date nacimiento;
    private String documento;
    private List<Contacto> datosContacto = new ArrayList<>();

    public DatosPersonales(String nombre, String apellido, Date nacimiento, String documento, List<Contacto> datosContacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.documento = documento;
        this.datosContacto = datosContacto;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Date getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public List<Contacto> getDatosContacto() {
        return datosContacto;
    }
    public void setDatosContacto(List<Contacto> datosContacto) {
        this.datosContacto = datosContacto;
    }
}
