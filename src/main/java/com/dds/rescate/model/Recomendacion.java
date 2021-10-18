package com.dds.rescate.model;


import java.util.HashMap;
import java.util.List;

public class Recomendacion {

    private PublicacionAdopcion adopcion;
    private int puntajeCaracteristicas;
    private int puntajeRequerimientos;
    private int total;
    private StringBuilder log_caracteristicas;
    private StringBuilder log_preguntas;

    public PublicacionAdopcion getAdopcion(){
        return adopcion;
    }

    public List<CaracteristicaMascota> getCaracteristicas(){
        return getAdopcion().getMascota().getCaracteristicas();
    }

    public List<CaracteristicaMascota> getPreguntas(){
        return getAdopcion().getPreguntas();
    }

    public String getPuntajeCaracteristicas(){
        return Integer.toString(puntajeCaracteristicas);
    }

    public String getPuntajePreguntas(){
        return Integer.toString(puntajeRequerimientos);
    }

    public String getTotal(){
        return Integer.toString(total);
    }

    public int getTotalInt(){
        return total;
    }

    public StringBuilder getLog_caracteristicas(){
        return log_caracteristicas;
    }

    public StringBuilder getLog_preguntas(){
        return log_preguntas;
    }

    public Recomendacion(PublicacionAdopcion adopcion, HashMap<String, Object> valorCaracteristicas, HashMap<String, Object> valorPreguntas){
        this.adopcion = adopcion;
        this.puntajeCaracteristicas = (int) valorCaracteristicas.get("valor");
        this.puntajeRequerimientos = (int) valorPreguntas.get("valor");
        this.total = (int) valorCaracteristicas.get("valor") + (int) valorPreguntas.get("valor");
        this.log_caracteristicas = (StringBuilder) valorCaracteristicas.get("log");
        this.log_preguntas = (StringBuilder) valorPreguntas.get("log");
    }

}
