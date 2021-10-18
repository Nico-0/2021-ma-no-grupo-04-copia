package com.dds.rescate.model;

import java.util.List;

public class Pregunta {//Caracteristica

    public Caracteristica pregunta;
    //public Boolean esGeneral;
    public List<String> respuestasPosibles;
    public List<Comparacion> valoresRespuestas;

    //Constructor
    public Pregunta(Caracteristica pregunta, List<String> respuestasPosibles, List<Comparacion> valoresRespuestas) {
        this.pregunta = pregunta;
        //this.esGeneral = esGeneral;
        this.respuestasPosibles = respuestasPosibles;
        this.valoresRespuestas = valoresRespuestas;
    }

    //Getters y Setters
    public Caracteristica getPregunta() {
        return pregunta;
    }

    public void setPregunta(Caracteristica pregunta) {
        this.pregunta = pregunta;
    }


    public int obtenerValor(String respuestaUno, String respuestaDos) {

        if (!respuestaUno.equals(respuestaDos)) {
            Comparacion valor = valoresRespuestas.stream().filter(v -> (v.respuesta_unoydos.contains(respuestaUno) && v.respuesta_unoydos.contains(respuestaDos))).findAny().orElse(null);

            if (valor == null) {
                //System.out.println("La asociacion no asigno el puntaje para el par: " + respuestaUno + " y " + respuestaDos);
                return 0;
            } else {
                return valor.getValor();
            }
        }else {
            //valores iguales
            return 10;
        }

    }

    public String getNombre(){
        return pregunta.getNombre();
    }

}
