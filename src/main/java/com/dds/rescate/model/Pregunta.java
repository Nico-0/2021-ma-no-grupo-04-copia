package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    public String preguntaNombre;
    public String preguntaDescripcion;
    //public Boolean esGeneral;
    public List<String> respuestasPosibles = new ArrayList<>();
    public List<Comparacion> valoresRespuestas = new ArrayList<>();

    //Constructor
    public Pregunta(String preguntaNombre, String preguntaDescripcion) {
        this.preguntaDescripcion = preguntaDescripcion;
        //this.esGeneral = esGeneral;
    }

    //Getters y Setters
    public String getPreguntaDescripcion() {
        return preguntaDescripcion;
    }

    public void setPreguntaDescripcion(String preguntaDescripcion) {
        this.preguntaDescripcion = preguntaDescripcion;
    }





}
