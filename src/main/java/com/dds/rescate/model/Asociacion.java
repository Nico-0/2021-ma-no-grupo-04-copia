package com.dds.rescate.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinTable(name = "asociacion_caracteristica", inverseJoinColumns = { @JoinColumn(name = "caracteristica_id") })
    public List<Pregunta> caracteristicasParaRegistrarMascota;

    @ManyToMany//(cascade=CascadeType.REMOVE)
    //@OnDelete(action= OnDeleteAction.CASCADE)
    @JoinTable(name = "asociacion_pregunta", inverseJoinColumns = { @JoinColumn(name = "pregunta_id") })
    public List<Pregunta> preguntasParaPublicarEnAdopcion;


    //Constructor
    public Asociacion(String nombre, List<Pregunta> caracteristicasParaRegistrarMascota, List<Pregunta> preguntasParaPublicarEnAdopcion) {
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

    public List<Pregunta> getCaracteristicas() {
        return caracteristicasParaRegistrarMascota;
    }

    public void setCaracteristicas(List<Pregunta> caracteristicas) {
        this.caracteristicasParaRegistrarMascota = caracteristicas;
    }

    public List<Pregunta> getPreguntasParaPublicarEnAdopcion() {
        return preguntasParaPublicarEnAdopcion;
    }

    public void setPreguntasParaPublicarEnAdopcion(List<Pregunta> preguntasParaPublicarEnAdopcion) {
        this.preguntasParaPublicarEnAdopcion = preguntasParaPublicarEnAdopcion;
    }


    //Metodos
    public void agregarCaracteristica(Pregunta caracteristicaNueva){
        this.caracteristicasParaRegistrarMascota.add(caracteristicaNueva);
    }
    public void agregarPregunta(Pregunta preguntaNueva){
        this.preguntasParaPublicarEnAdopcion.add(preguntaNueva);
    }


    public List<String> getCaracteristicasString() {
        return caracteristicasParaRegistrarMascota.stream().map(caracteristica -> caracteristica.getNombre()).collect(Collectors.toList());
    }

    public List<String> getPreguntasString() {
        return preguntasParaPublicarEnAdopcion.stream().map(caracteristica -> caracteristica.getNombre()).collect(Collectors.toList());
    }

    private List<String> getCaracteristicasMascotaString(List<CaracteristicaMascota> caracteristicasDadas) {
        return caracteristicasDadas.stream().map(caracteristica -> caracteristica.getCaracteristica().getNombre()).collect(Collectors.toList());
    }

    public void validarCaracteristicasAsociacion(List<CaracteristicaMascota> caracteristicasDadas){
        if(caracteristicasParaRegistrarMascota != null) {
            List<String> caracteristicasAsociacion = getCaracteristicasString();
            List<String> caracteristicasMascota = getCaracteristicasMascotaString(caracteristicasDadas);
            Boolean contieneTodas = caracteristicasMascota.containsAll(caracteristicasAsociacion);
            if (!contieneTodas)
                throw new RuntimeException("No se completaron todas las caracteristicas que pide la asociacion");
        }
    }

    public void validarPreguntasAsociacion(List<CaracteristicaMascota> preguntasDadas){
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

    public Pregunta getPreguntaByNombre(String nombrePregunta){
        return preguntasParaPublicarEnAdopcion.stream().filter(p -> p.getPregunta().getNombre().equals(nombrePregunta)).findAny().orElse(null);
    }

    public Pregunta getCaracteristicaByNombre(String nombrePregunta){
        return caracteristicasParaRegistrarMascota.stream().filter(p -> p.getPregunta().getNombre().equals(nombrePregunta)).findAny().orElse(null);
    }

}
