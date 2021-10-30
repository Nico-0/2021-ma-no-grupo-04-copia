package com.dds.rescate.service;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.TipoMascota;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Recomendador {

    private Recomendador(){}

    private EntityManager entityManager;

    public Recomendador(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //generar recomendaciones para una intencion de adopcion
    public void recomendar(PublicacionIntencionDeAdopcion intencion){
        intencion.getRecomendaciones().clear(); //borrar recomendaciones viejas
        intencion.updateFechaReco();

        PublicacionService repo = new PublicacionService(entityManager);
        List<Publicacion> publisAdopcion = repo.getDeTipo("adopcion");

        publisAdopcion = filtrarTipoMascota(publisAdopcion, intencion);

        publisAdopcion.stream().forEach(adopcion -> generarRecomendacion(intencion, (PublicacionAdopcion) adopcion));
        //TODO verificar que solo se recomiende el mismo tipo de mascota?


        intencion.ordenarRecomendaciones();
    }

    private List<Publicacion> filtrarTipoMascota(List<Publicacion> publisAdopcion, PublicacionIntencionDeAdopcion intencion){
        TipoMascota tipo = intencion.getTipoMascota();
        return publisAdopcion.stream().filter(p -> p.getTipoMascota() == tipo).collect(Collectors.toList());
    }

    //obtener valor de matcheo de una publicacion con otra
    //generar reco y guardar
    private void generarRecomendacion(PublicacionIntencionDeAdopcion intencion, PublicacionAdopcion adopcion) {
        HashMap<String, Object> valorPreferencias = calcularValor(intencion.getPreferencias(), adopcion.getMascota().getCaracteristicas(), intencion.getAsociacionAsignada(), "");
        HashMap<String, Object> valorPreguntas = calcularValor(intencion.getPreguntas(), adopcion.getPreguntas(), intencion.getAsociacionAsignada(), "preguntas");
        Recomendacion recomendacion = new Recomendacion(adopcion, valorPreferencias, valorPreguntas);
        entityManager.persist(recomendacion);
        intencion.addRecomendacion(recomendacion);
    }


    public HashMap<String, Object> calcularValor(List<Respuesta> preguntasIntencion, List<Respuesta> preguntasAdopcion, Asociacion asociacion, String tipo){
        int valor = 0;
        Respuesta pregunta;
        String nombre;
        String respuestaIntencion;
        String respuestaAdopcion;
        Respuesta respuestaAdopcionCaracteristica;
        RespuestasAsociacion preguntaAsociacion;
        int temp;
        StringBuilder log = new StringBuilder();
        String n = System.lineSeparator();
        HashMap<String, Object> resultado = new HashMap<>();


        //por cada pregunta en la intencion
        for (int i=0; i<preguntasIntencion.size(); i++) {
            pregunta = preguntasIntencion.get(i);
            nombre = pregunta.getNombre();

            //respuesta de la intencion
            respuestaIntencion = pregunta.getValor();

            //respuesta de la adopcion
            String finalNombre = nombre;
            respuestaAdopcionCaracteristica = preguntasAdopcion.stream().filter(p -> p.getCaracteristica().getNombre().equals(finalNombre)).findAny().orElse(null);


            //obtener valor para el par
            if(respuestaAdopcionCaracteristica != null){
                respuestaAdopcion = respuestaAdopcionCaracteristica.getValor();
                //llamar a la asociacion para obtener el valor
                if(tipo.equals("preguntas")) {
                    preguntaAsociacion = asociacion.getPreguntaByNombre(nombre);
                }
                else {
                    preguntaAsociacion = asociacion.getCaracteristicaByNombre(nombre);
                }
                if(preguntaAsociacion != null){
                    temp = preguntaAsociacion.obtenerValor(respuestaIntencion, respuestaAdopcion);
                    valor = valor + temp;
                    log.append(nombre+" --> "+respuestaIntencion+" y "+respuestaAdopcion+": "+Integer.toString(temp)+" puntos"+n);
                }else{
                    //se suma 0 a valor
                    log.append("La asociacion no contiene la pregunta/caracteristica "+nombre+n);
                }

            }
            else{
                //se suma 0 a valor
                log.append("La publicacion en adopcion no contiene la pregunta/caracteristica: "+nombre+n);
            }

        }

        resultado.put("valor", valor);
        resultado.put("log", log);
        return resultado;
    }






//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    private static Recomendador instance;
    public static Recomendador getInstance() {
        if(instance == null) {
            instance = new Recomendador();
        }
        return instance;
    }

    public int caracteristicasCoincidentes(List<Respuesta> preferencias, List<Respuesta> caracteristicas){
        List<String> caracteristicasString = getCaracteristicasString(caracteristicas);
        Respuesta preferencia;
        String nombre;
        String valor;
        String valor2;
        int coincidencias = 0;

        for (int i=0; i<preferencias.size(); i++){
            //System.out.println("Estoy en el for: "+ i +" de "+ (preferencias.size()-1) + " para " + preferencias.get(i).getNombre());
            preferencia = preferencias.get(i);
            nombre = preferencia.getNombre();
            valor = preferencia.getValor();

            if(caracteristicasString.contains(nombre)){
                String finalNombre = nombre;
                valor2 = caracteristicas.stream().filter(c -> c.getNombre().equals(finalNombre)).findAny().orElse(null).getValor();
                if(valor.equals(valor2)){
                    coincidencias++;
                    //System.out.println(valor + " = " + valor2);
                }
                else{
                    //System.out.println(valor + " != " + valor2);
                }
            }
            else{
                //System.out.println(caracteristicasString + " no contiene " + nombre);
            }

        }
        //System.out.println("Devuelvo coincidencias: "+ coincidencias + "\n\n");
        return coincidencias;
    }


    public List<String> getCaracteristicasString(List<Respuesta> caracteristicas) {
        return caracteristicas.stream().map(Respuesta::getNombre).collect(Collectors.toList());
    }

/*
    private void generarRecomendacion(PublicacionIntencionDeAdopcion intencion, PublicacionAdopcion adopcion) {
        int cantPreferencias = caracteristicasCoincidentes(intencion.getPreferencias(), adopcion.getMascota().getCaracteristicas());
        int cantComodidades = caracteristicasCoincidentes(intencion.getComodidades(), adopcion.getRequerimientos());
        Recomendacion recomendacion = new Recomendacion(adopcion, cantPreferencias, cantComodidades);

        intencion.addRecomendacion(recomendacion);
    }
*/


}

