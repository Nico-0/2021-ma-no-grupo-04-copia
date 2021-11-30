package com.dds.rescate.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Comparacion {
    @Id
    @GeneratedValue
    public int ID;
    //public List<String> respuesta_unoydos;
    public String respuestaUno;
    public String respuestaDos;
    public int valor; // de 0 a 10

    public int getValor(){
        return valor;
    }

    public Comparacion(String respuestaUno, String respuestaDos, int valor){
        //this.respuesta_unoydos = Arrays.asList(respuestaUno, respuestaDos);
        this.respuestaUno = respuestaUno;
        this.respuestaDos = respuestaDos;
        this.valor = valor;
    }
    private Comparacion(){}

    public String toString(){
        return "'"+respuestaUno+"' y '"+respuestaDos+"' --> "+valor+" puntos";
    }

}
