package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

public class PublicacionService {

    //Metodos
    public void confirmarMascotaEncontrada(Mascota mascotaPerdida, Formulario formulario){
        //TODO
    }
    public void quitarPublicacion(Publicacion publicacion){
        //TODO
    }
    public void generarPublicacion(Formulario formularioPubli){
        //TODO
    }
    public void aceptarPublicacion (Publicacion publicacionNueva){
        //TODO
    }
    public void generarInteresAdopcion(String descripcion){
        //TODO
    }
    public List<Publicacion> obtenerMascotasDisponiblesParaAdoptar(){

        List<Publicacion> publis = new ArrayList<Publicacion>();

        return publis;
    }
    public  PublicacionService getInstance(){
        return new PublicacionService();
    }

}
