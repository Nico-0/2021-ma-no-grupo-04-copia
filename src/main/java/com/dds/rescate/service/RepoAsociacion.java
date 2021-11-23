package com.dds.rescate.service;

import com.dds.rescate.model.Asociacion;
import com.dds.rescate.model.PreguntaCaracteristica;
import com.dds.rescate.model.Respuesta;
import com.dds.rescate.model.Usuario;
import com.dds.rescate.util.Global;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class RepoAsociacion {

    public RepoAsociacion() {
    }
    private EntityManager entityManager;

    public RepoAsociacion(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Asociacion> getAsociaciones() {
        return entityManager.createQuery("from Asociacion", Asociacion.class).getResultList();
    }

    public Asociacion getAsociacion1(){
        return entityManager.createQuery("from Asociacion a where a.nombre = ?1", Asociacion.class)
                .setParameter(1, "patitas al rescate")
                .getSingleResult();
    }

    public Asociacion getAsociacionByNombre(String nombre){
        return entityManager.createQuery("from Asociacion a where a.nombre = ?1", Asociacion.class)
                .setParameter(1, nombre)
                .getSingleResult();
    }

    public List<Respuesta> crearRespuestasGenericasCaracAsoc1(){
        Asociacion asociacion = getAsociacion1();
        PreguntaCaracteristica color = asociacion.getCaracteristicaByNombre("color").getPregunta();
        PreguntaCaracteristica tamanio = asociacion.getCaracteristicaByNombre("tamanio").getPregunta();
        PreguntaCaracteristica pelo = asociacion.getCaracteristicaByNombre("pelo").getPregunta();
        PreguntaCaracteristica cola = asociacion.getCaracteristicaByNombre("cola").getPregunta();

        List<Respuesta> respuestas = Arrays.asList(new Respuesta(color, "blanco"),
                new Respuesta(tamanio, "mediano"), new Respuesta(pelo, "corto"), new Respuesta(cola, "larga"));
        respuestas.forEach(entityManager::persist);
        return respuestas;
    }


    public List<Respuesta> crearRespuestasGenericasPregAsoc1(){
        //List<String> preguntasGenerales = Global.getInstance().getPreguntasGenerales(); //no va, por mas que sea global se la guarda la asociacion como propia
        //List<String> preguntasAsociacion = asociacion.getPreguntasString(); //usar en el caso de hacer luego metodo generico

        Asociacion asociacion = getAsociacion1();
        PreguntaCaracteristica con_patio = asociacion.getPreguntaByNombre("con_patio").getPregunta();
        PreguntaCaracteristica otros_animales = asociacion.getPreguntaByNombre("otros_animales").getPregunta();
        PreguntaCaracteristica casa_grande = asociacion.getPreguntaByNombre("casa_grande").getPregunta();
        PreguntaCaracteristica alto_presupuesto = asociacion.getPreguntaByNombre("alto_presupuesto").getPregunta();
        PreguntaCaracteristica atencion_8hs = asociacion.getPreguntaByNombre("atencion_8hs").getPregunta();

        List<Respuesta> respuestas = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "no"),
                new Respuesta(alto_presupuesto, "no"), new Respuesta(atencion_8hs, "1h"));
        respuestas.forEach(entityManager::persist);
        return respuestas;
    }



}
