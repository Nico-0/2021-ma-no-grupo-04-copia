package com.dds.rescate.model;


import com.dds.rescate.exception.ChapitaException;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChapitaEncontrada {

    @Id
    @GeneratedValue
    public int ID;

    @ManyToOne //puede estar el mismo rescatista en muchas chapitas
    UsuarioDuenio rescatista;

    String id_chapita;

    @ManyToOne //puede perderse y volver a encontrarse varias veces la misma mascota
    Mascota mascota_asociada;

    @ManyToOne //puede estar el mismo dueño en muchas chapitas
    UsuarioDuenio duenio_mascota;

    Date fecha_rescate;

    Boolean publicacion_finalizada;

    public ChapitaEncontrada(UsuarioDuenio rescatista, String id_chapita, EntityManager entityManager){
        this.rescatista = rescatista;
        this.id_chapita = id_chapita;
        this.mascota_asociada = entityManager.createQuery("from Mascota m where m.id_chapita = ?1", Mascota.class)
                .setParameter(1, id_chapita)
                .getSingleResult();
        if(!mascota_asociada.estaPerdida()){
            throw new ChapitaException("La mascota asociada a la chapita no se encuentra perdida");
        }
        this.duenio_mascota = mascota_asociada.getDuenio(entityManager);
        if(duenio_mascota == null){
            throw new RuntimeException("No se encontró el duenio de la mascota");
        }
        this.fecha_rescate = new Date();
        this.publicacion_finalizada = false;
    }

    private ChapitaEncontrada(){}

    public void finalizar(){
        this.publicacion_finalizada = true;
    }

    public String getRescatistaUserString(){
        return rescatista.getUsername();
    }
    public String getRescatistaString(){
        return rescatista.getNombreCompleto();
    }
    public String getFechaRescate(){
        return fecha_rescate.toString();
    }
    public Mascota getMascota_asociada(){
        return mascota_asociada;
    }

}
