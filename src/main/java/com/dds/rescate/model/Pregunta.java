package com.dds.rescate.model;

public class Pregunta {
    public String preguntaDescripcion;
    public Boolean esGeneral;

    //Constructor
    public Pregunta(String preguntaDescripcion, Boolean esGeneral) {
        this.preguntaDescripcion = preguntaDescripcion;
        this.esGeneral = esGeneral;
    }

    //Getters y Setters
    public String getPreguntaDescripcion() {
        return preguntaDescripcion;
    }

    public void setPreguntaDescripcion(String preguntaDescripcion) {
        this.preguntaDescripcion = preguntaDescripcion;
    }

    public Boolean getEsGeneral() {
        return esGeneral;
    }

    public void setEsGeneral(Boolean esGeneral) {
        this.esGeneral = esGeneral;
    }
}
