package com.dds.rescate.model;

import com.dds.rescate.model.Enum.TipoPregunta;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pregunta {//Caracteristica
    @Id
    @GeneratedValue
    public int ID;

    @Embedded
    public Caracteristica pregunta;

    @ElementCollection
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name = "Pregunta_ID")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> respuestasPosibles;

    @OneToMany
    @JoinColumn(name = "FK_pregunta")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Comparacion> valoresRespuestas;

    //Constructor
    public Pregunta(Caracteristica pregunta, List<String> respuestasPosibles, List<Comparacion> valoresRespuestas) {
        this.pregunta = pregunta;
        //this.esGeneral = esGeneral;
        this.respuestasPosibles = respuestasPosibles;
        this.valoresRespuestas = valoresRespuestas;
    }
    private Pregunta(){}

    //Getters y Setters
    public Caracteristica getPregunta() {
        return pregunta;
    }

    public void setPregunta(Caracteristica pregunta) {
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

}
