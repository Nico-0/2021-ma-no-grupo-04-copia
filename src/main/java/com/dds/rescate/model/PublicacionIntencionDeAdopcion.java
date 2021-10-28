package com.dds.rescate.model;

import com.dds.rescate.model.Enum.TipoMascota;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class PublicacionIntencionDeAdopcion extends Publicacion {

    @OneToMany//(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_publicacion_intencion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<CaracteristicaMascota> preferencias;

    @OneToMany
    @JoinColumn(name = "FK_publicacion_intencion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<CaracteristicaMascota> preguntas;

    @OneToMany
    @JoinColumn(name = "FK_publicacion_intencion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Recomendacion> recomendaciones = new ArrayList<>();

    public String ultimaRecomendacion;

    //Constructor
    public PublicacionIntencionDeAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota,
                                          List<CaracteristicaMascota> preferenciasPubli, List<CaracteristicaMascota> preguntasPubli) {
        super(autor, asociacionAsignada, tipoMascota);
        asociacionAsignada.validarCaracteristicasAsociacion(preferenciasPubli);
        asociacionAsignada.validarPreguntasAsociacion(preguntasPubli);
        //TODO validar respuestas a las preguntas y caracteristicas
        this.preferencias = preferenciasPubli;
        this.preguntas = preguntasPubli;
        this.ultimaRecomendacion = "nunca";
    }
    private PublicacionIntencionDeAdopcion(){

    }

    //Getters y Setters
    public List<CaracteristicaMascota> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<CaracteristicaMascota> preferencias) {
        this.preferencias = preferencias;
    }

    public List<CaracteristicaMascota> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<CaracteristicaMascota> preguntas) {
        this.preguntas = preguntas;
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
