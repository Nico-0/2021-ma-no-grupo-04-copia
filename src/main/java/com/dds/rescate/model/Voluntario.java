package com.dds.rescate.model;

public class Voluntario extends Usuario{
    public Asociacion asociacion;

    //Constructor
    public Voluntario(String username, String password, Asociacion asociacion){
        super(username, password);
        this.asociacion = asociacion;
    }

    //Getters y Setters
    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    //Metodos
    public void aceptarPublicacion(Publicacion publicacion){
        //Todo
    }
}
