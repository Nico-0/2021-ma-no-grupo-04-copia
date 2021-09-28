package com.dds.rescate.model;

import com.dds.rescate.model.Enum.TipoMascota;

import java.util.List;

public class PublicacionIntencionDeAdopcion extends Publicacion {

    List<Caracteristica> preferencias;
    List<Extra> comodidades;

    //Constructor
    public PublicacionIntencionDeAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota,
                                          List<Caracteristica> preferenciasPubli,
                                          List<Extra> comodidadesPubli) {
        super(autor, asociacionAsignada, tipoMascota);
        this.preferencias = preferenciasPubli;
        this.comodidades = comodidadesPubli;
    }
    //Getters y Setters
    public List<Caracteristica> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Caracteristica> preferencias) {
        this.preferencias = preferencias;
    }

    public List<Extra> getComodidades() {
        return comodidades;
    }

    public void setComodidades(List<Extra> comodidades) {
        this.comodidades = comodidades;
    }

    //Metodos
    public void contactar(UsuarioDuenio duenioNuevo){
        //TODO
    }

    public void confirmarAdopcion(Mascota mascotaAdoptada){
        //TODO
    }

    public void confirmarAdopcion(PublicacionAdopcion publicacion){
        //TODO
    }

    @Override
    public String getTipoPublihbs(){
        return "publiIntencion.hbs";
    }

    @Override
    public String getTipoPubli(){
        return "intencion";
    }

}
