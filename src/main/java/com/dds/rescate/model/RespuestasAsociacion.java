package com.dds.rescate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class RespuestasAsociacion {
    @Id
    @GeneratedValue
    public int ID;

    @ManyToOne //la misma pregunta pueden responderla varias asociaciones
    public PreguntaCaracteristica pregunta;

    @ElementCollection
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name = "RespuestasAsociacion_ID")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> respuestasPosibles;

    @OneToMany //es dificil de ver pero, al menos en la instancia de datos, todos los List<Comparacion> valoresRespuestas entran en un unico objeto RespuestasAsociacion
    @JoinColumn(name = "FK_respuestasAsociacion")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Comparacion> valoresRespuestas;

    //Constructor
    public RespuestasAsociacion(PreguntaCaracteristica pregunta, List<String> respuestasPosibles, List<Comparacion> valoresRespuestas) {
        this.pregunta = pregunta;
        //this.esGeneral = esGeneral;
        this.respuestasPosibles = respuestasPosibles;
        this.valoresRespuestas = valoresRespuestas;
    }
    private RespuestasAsociacion(){}

    //Getters y Setters
    public PreguntaCaracteristica getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaCaracteristica pregunta) {
        this.pregunta = pregunta;
    }


    public int obtenerValor(String respuestaUno, String respuestaDos) {

        if (!respuestaUno.equals(respuestaDos)) {
            Comparacion valor = obtenerComparacion(respuestaUno, respuestaDos);

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

    private Comparacion obtenerComparacion(String respuestaUno, String respuestaDos){
        //return valoresRespuestas.stream().filter(v -> (v.respuesta_unoydos.contains(respuestaUno) && v.respuesta_unoydos.contains(respuestaDos))).findAny().orElse(null);

        return valoresRespuestas.stream().filter(v -> ( (v.respuestaUno.equals(respuestaUno) || v.respuestaDos.equals(respuestaUno)) &&
                                                        (v.respuestaUno.equals(respuestaDos) || v.respuestaDos.equals(respuestaDos))   )   ).findAny().orElse(null);
    }

    public String getNombre(){
        return pregunta.getNombre();
    }

    public List<String> getRespuestasPosibles(){
        return respuestasPosibles;
    }

    public List<String> getComparaciones(){
        return valoresRespuestas.stream().map(Comparacion::toString).collect(Collectors.toList());
    }

}
