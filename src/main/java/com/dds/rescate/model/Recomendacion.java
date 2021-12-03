package com.dds.rescate.model;


import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
public class Recomendacion {

    @Id
    @GeneratedValue
    public int ID;
    @ManyToOne //si el usuario tiene varias intenciones entonces la misma adopcion se puede recomendar varias veces
    private PublicacionAdopcion adopcion;
    private int puntajeCaracteristicas;
    private int puntajePreguntas;
    private int total;

    @Column(length = 1000)
    private StringBuilder log_caracteristicas;
    @Column(length = 1000)
    private StringBuilder log_preguntas;

    private Recomendacion(){}

    public PublicacionAdopcion getAdopcion(){
        return adopcion;
    }

    public List<Respuesta> getCaracteristicas(){
        return getAdopcion().getMascota().getCaracteristicas();
    }

    public List<Respuesta> getPreguntas(){
        return getAdopcion().getPreguntas();
    }

    public String getPuntajeCaracteristicas(){
        return Integer.toString(puntajeCaracteristicas);
    }

    public String getPuntajePreguntas(){
        return Integer.toString(puntajePreguntas);
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
        this.puntajePreguntas = (int) valorPreguntas.get("valor");
        this.total = (int) valorCaracteristicas.get("valor") + (int) valorPreguntas.get("valor");
        this.log_caracteristicas = (StringBuilder) valorCaracteristicas.get("log");
        this.log_preguntas = (StringBuilder) valorPreguntas.get("log");
    }

}
