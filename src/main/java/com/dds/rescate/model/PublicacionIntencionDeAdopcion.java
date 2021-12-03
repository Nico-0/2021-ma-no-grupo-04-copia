package com.dds.rescate.model;

import com.dds.rescate.model.Enum.EstadoPubli;
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

    @OneToMany(cascade = {CascadeType.ALL})//Todas las respuestas son unicas, creadas al instanciar la mascota
    @JoinColumn(name = "FK_intencion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Respuesta> preferencias;

    @OneToMany(cascade = {CascadeType.ALL})//Todas las respuestas son unicas, creadas al instanciar la mascota
    @JoinColumn(name = "FK_intencion")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Respuesta> preguntas;

    @OneToMany //al recomendar se cargan en esta lista y siempre son instancias nuevas
    @JoinColumn(name = "FK_intencion")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderColumn(name = "orden") //TODO no anda??????????????????
    List<Recomendacion> recomendaciones = new ArrayList<>();

    public String ultimaRecomendacion;

    //Constructor
    public PublicacionIntencionDeAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota,
                                          List<Respuesta> preferenciasPubli, List<Respuesta> preguntasPubli) {
        super(autor, asociacionAsignada, tipoMascota);
        asociacionAsignada.validarCaracteristicasAsociacion(preferenciasPubli);
        asociacionAsignada.validarPreguntasAsociacion(preguntasPubli);
        //validar respuestas a las preguntas y caracteristicas, en el caso de implementar el ingreso manual de estas
        this.preferencias = preferenciasPubli;
        this.preguntas = preguntasPubli;
        this.ultimaRecomendacion = "nunca";
        this.estadoPublicacion = EstadoPubli.PUBLICADA; //no necesitan aprobarse las intenciones ya que no aparecen en el muro
    }

    private PublicacionIntencionDeAdopcion(){}

    //Getters y Setters
    public List<Respuesta> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Respuesta> preferencias) {
        this.preferencias = preferencias;
    }

    public List<Respuesta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Respuesta> preguntas) {
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

    @Override
    public String getTipoPublihbs(){
        return "publiIntencion.hbs";
    }

    @Override
    public String getTipoPubli(){
        return "intencion";
    }

    @Override
    public void ordenarRecomendaciones() {
        recomendaciones = recomendaciones.stream().sorted(Comparator.comparingInt(Recomendacion::getTotalInt).reversed()).collect(Collectors.toList());
    }

}
