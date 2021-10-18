package com.dds.rescate.model;

import java.util.Arrays;
import java.util.List;

public class Comparacion {

    public List<String> respuesta_unoydos;
    public int valor; // de 0 a 10

    public int getValor(){
        return valor;
    }

    public Comparacion(String respuestaUno, String respuestaDos, int valor){
        this.respuesta_unoydos = Arrays.asList(respuestaUno, respuestaDos);
        this.valor = valor;
    }

}
