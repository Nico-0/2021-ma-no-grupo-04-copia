package com.dds.rescate.model;

import javax.persistence.Entity;

@Entity
public class UsuarioVoluntario extends Usuario{
    //Constructor
    public UsuarioVoluntario(String username, String password, Asociacion asociacion){
        super(username, password);
        this.asociacion = asociacion;
    }
    private UsuarioVoluntario(){}

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

    @Override
    public String getTipo(){
        return "voluntario";
    }
}
