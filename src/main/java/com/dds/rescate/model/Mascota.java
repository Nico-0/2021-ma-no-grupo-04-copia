package com.dds.rescate.model;

public class Mascota {
    private String tipoMascota;
    private String nombre;
    private String apodo;
    private String descripcion;
    private String caracteristicas;
    private Sexo sexo;

    public Mascota(String tipoMascota, String nombre, String apodo, String descripcion, String caracteristicas, Sexo sexo) {
        this.tipoMascota = tipoMascota;
        this.nombre = nombre;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.sexo = sexo;
    }
    
    public Mascota() {}

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristica) {
        this.caracteristicas = caracteristica;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
