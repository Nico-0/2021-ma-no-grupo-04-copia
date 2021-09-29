package com.dds.rescate.model;

import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {

    public String nombre;
    public List<Caracteristica> caracteristicas;
    public List<Pregunta> preguntasParaPublicarEnAdopcion; //para hacer una publicacion mas detallada, no tiene otra funcionalidad

    //Constructor

    public Asociacion(String nombre, List<Caracteristica> caracteristicas, List<Pregunta> preguntasParaPublicarEnAdopcion, List<Pregunta> preguntasGenerales) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.preguntasParaPublicarEnAdopcion = preguntasParaPublicarEnAdopcion;
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


    //Metodos
    public void agregarCaracteristica(Caracteristica caracteristicaNueva){
        this.caracteristicas.add(caracteristicaNueva);
    }
    public void agregarPregunta(Pregunta preguntaNueva){
        this.preguntasParaPublicarEnAdopcion.add(preguntaNueva);
    }

    public void validarCaracteristicasAsociacion(List<CaracteristicaMascota> caracteristicasDadas) {
        if(caracteristicas != null) {
            List<String> caracteristicasAsociacion = getCaracteristicasString();
            List<String> caracteristicasMascota = getCaracteristicasMascotaString(caracteristicasDadas);
            Boolean contieneTodas = caracteristicasMascota.containsAll(caracteristicasAsociacion);
            if (!contieneTodas)
                throw new RuntimeException("No se completaron todas las caracteristicas que pide la asociacion");
        }
    }

    public List<String> getCaracteristicasString() {
        return caracteristicas.stream().map(caracteristica -> caracteristica.getNombre()).collect(Collectors.toList());
    }

    private List<String> getCaracteristicasMascotaString(List<CaracteristicaMascota> caracteristicasDadas) {
        return caracteristicasDadas.stream().map(caracteristica -> caracteristica.getCaracteristica().getNombre()).collect(Collectors.toList());
    }


}
