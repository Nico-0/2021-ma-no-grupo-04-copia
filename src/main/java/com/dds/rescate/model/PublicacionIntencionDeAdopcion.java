package com.dds.rescate.model;

import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.util.Global;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PublicacionIntencionDeAdopcion extends Publicacion {

    List<CaracteristicaMascota> preferencias;
    List<CaracteristicaMascota> comodidades;
    List<Recomendacion> recomendaciones = new ArrayList<>();
    public String ultimaRecomendacion;

    //Constructor
    public PublicacionIntencionDeAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota,
                                          List<CaracteristicaMascota> preferenciasPubli, List<CaracteristicaMascota> comodidadesPubli) {
        super(autor, asociacionAsignada, tipoMascota);
        asociacionAsignada.validarCaracteristicasAsociacion(preferenciasPubli);
        Global.getInstance().validarRequerimientosGenerales(comodidadesPubli);
        this.preferencias = preferenciasPubli;
        this.comodidades = comodidadesPubli;
        this.ultimaRecomendacion = "nunca";
    }
    //Getters y Setters
    public List<CaracteristicaMascota> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<CaracteristicaMascota> preferencias) {
        this.preferencias = preferencias;
    }

    public List<CaracteristicaMascota> getComodidades() {
        return comodidades;
    }

    public void setComodidades(List<CaracteristicaMascota> comodidades) {
        this.comodidades = comodidades;
    }

    public List<Recomendacion> getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(List<Recomendacion> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public void addRecomendacion(Recomendacion recomendacion){
        this.recomendaciones.add(recomendacion);
    }

    public void updateFechaReco(){
        this.ultimaRecomendacion = new Date().toString();
    }

    public String getUltimaRecomendacion(){
        return ultimaRecomendacion;
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

    public void ordenarRecomendaciones() {
        recomendaciones = recomendaciones.stream().sorted(Comparator.comparing(Recomendacion::getTotal).reversed()).collect(Collectors.toList());
    }

}
