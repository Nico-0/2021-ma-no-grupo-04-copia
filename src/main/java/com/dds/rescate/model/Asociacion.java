package com.dds.rescate.model;

import java.util.List;

public class Asociacion {

    public String nombre;
    public List<Caracteristica> caracteristicas;
    public List<Pregunta> preguntasParaPublicarEnAdopcion;
    public List<Pregunta> preguntasGenerales;

    //Constructor

    public Asociacion(String nombre, List<Caracteristica> caracteristicas, List<Pregunta> preguntasParaPublicarEnAdopcion, List<Pregunta> preguntasGenerales) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.preguntasParaPublicarEnAdopcion = preguntasParaPublicarEnAdopcion;
        this.preguntasGenerales = preguntasGenerales;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Pregunta> getPreguntasParaPublicarEnAdopcion() {
        return preguntasParaPublicarEnAdopcion;
    }

    public void setPreguntasParaPublicarEnAdopcion(List<Pregunta> preguntasParaPublicarEnAdopcion) {
        this.preguntasParaPublicarEnAdopcion = preguntasParaPublicarEnAdopcion;
    }

    public List<Pregunta> getPreguntasGenerales() {
        return preguntasGenerales;
    }

    public void setPreguntasGenerales(List<Pregunta> preguntasGenerales) {
        this.preguntasGenerales = preguntasGenerales;
    }

    //Metodos
    public void agregarCaracteristica(Caracteristica caracteristicaNueva){
        this.caracteristicas.add(caracteristicaNueva);
    }
    public void agregarPregunta(Pregunta preguntaNueva){
        this.preguntasGenerales.add(preguntaNueva);
    }
}
