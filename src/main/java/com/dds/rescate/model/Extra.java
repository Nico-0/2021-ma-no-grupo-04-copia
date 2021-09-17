package com.dds.rescate.model;

public class Extra {
    public String nombre;
    public Boolean valor;

    //Constructor
    public Extra(String nombre, Boolean valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getValor() {
        return valor;
    }

    public void setValor(Boolean valor) {
        this.valor = valor;
    }

    //Metodos
}
