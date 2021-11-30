package com.dds.rescate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Asociacion {
    @Id
    @GeneratedValue
    public int ID;
    public String nombre;

    @ManyToMany//(cascade=CascadeType.REMOVE)
    //@OnDelete(action= OnDeleteAction.CASCADE)
    @JoinTable(name = "asociacion_respuestas_caracteristica", inverseJoinColumns = { @JoinColumn(name = "caracteristica_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<RespuestasAsociacion> caracteristicasParaRegistrarMascota;

    @ManyToMany//(cascade=CascadeType.REMOVE)
    //@OnDelete(action= OnDeleteAction.CASCADE)
    @JoinTable(name = "asociacion_respuestas_pregunta", inverseJoinColumns = { @JoinColumn(name = "pregunta_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<RespuestasAsociacion> preguntasParaPublicarEnAdopcion;


    //Constructor
    public Asociacion(String nombre, List<RespuestasAsociacion> caracteristicasParaRegistrarMascota, List<RespuestasAsociacion> preguntasParaPublicarEnAdopcion) {
        this.nombre = nombre;
        this.caracteristicasParaRegistrarMascota = caracteristicasParaRegistrarMascota;
        this.preguntasParaPublicarEnAdopcion = preguntasParaPublicarEnAdopcion;
    }
    private Asociacion(){

    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<RespuestasAsociacion> getCaracteristicas() {
        return caracteristicasParaRegistrarMascota;
    }

    public void setCaracteristicas(List<RespuestasAsociacion> caracteristicas) {
        this.caracteristicasParaRegistrarMascota = caracteristicas;
    }

    public List<RespuestasAsociacion> getPreguntas() {
        return preguntasParaPublicarEnAdopcion;
    }

    public void setPreguntas(List<RespuestasAsociacion> preguntasParaPublicarEnAdopcion) {
        this.preguntasParaPublicarEnAdopcion = preguntasParaPublicarEnAdopcion;
    }


    //Metodos
    public void agregarCaracteristica(RespuestasAsociacion caracteristicaNueva){
        this.caracteristicasParaRegistrarMascota.add(caracteristicaNueva);
    }
    public void agregarPregunta(RespuestasAsociacion preguntaNueva){
        this.preguntasParaPublicarEnAdopcion.add(preguntaNueva);
    }


    public List<String> getCaracteristicasString() {
        return caracteristicasParaRegistrarMascota.stream().map(caracteristica -> caracteristica.getNombre()).collect(Collectors.toList());
    }

    public List<String> getPreguntasString() {
        return preguntasParaPublicarEnAdopcion.stream().map(caracteristica -> caracteristica.getNombre()).collect(Collectors.toList());
    }

    private List<String> getCaracteristicasMascotaString(List<Respuesta> caracteristicasDadas) {
        return caracteristicasDadas.stream().map(caracteristica -> caracteristica.getCaracteristica().getNombre()).collect(Collectors.toList());
    }

    public void validarCaracteristicasAsociacion(List<Respuesta> caracteristicasDadas){
        if(caracteristicasParaRegistrarMascota != null) {
            List<String> caracteristicasAsociacion = getCaracteristicasString();
            List<String> caracteristicasMascota = getCaracteristicasMascotaString(caracteristicasDadas);
            Boolean contieneTodas = caracteristicasMascota.containsAll(caracteristicasAsociacion);
            if (!contieneTodas)
                throw new RuntimeException("No se completaron todas las caracteristicas que pide la asociacion");
        }
    }

    public void validarPreguntasAsociacion(List<Respuesta> preguntasDadas){
        if(preguntasParaPublicarEnAdopcion != null) {
            if (caracteristicasParaRegistrarMascota != null) {
                List<String> caracteristicasAsociacion = getPreguntasString();
                List<String> caracteristicasMascota = getCaracteristicasMascotaString(preguntasDadas);
                Boolean contieneTodas = caracteristicasMascota.containsAll(caracteristicasAsociacion);
                if (!contieneTodas)
                    throw new RuntimeException("No se completaron todas las preguntas que pide la asociacion");
            }
        }
    }

    public RespuestasAsociacion getPreguntaByNombre(String nombrePregunta){
        return preguntasParaPublicarEnAdopcion.stream().filter(p -> p.getPregunta().getNombre().equals(nombrePregunta)).findAny().orElse(null);
    }

    public RespuestasAsociacion getCaracteristicaByNombre(String nombrePregunta){
        return caracteristicasParaRegistrarMascota.stream().filter(p -> p.getPregunta().getNombre().equals(nombrePregunta)).findAny().orElse(null);
    }



}
