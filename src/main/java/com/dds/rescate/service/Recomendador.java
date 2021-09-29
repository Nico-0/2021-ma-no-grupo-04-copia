package com.dds.rescate.service;

import com.dds.rescate.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Recomendador {

    private static Recomendador instance;

    public static Recomendador getInstance() {
        if(instance == null) {
            instance = new Recomendador();
        }
        return instance;
    }

    public int caracteristicasCoincidentes(List<CaracteristicaMascota> preferencias, List<CaracteristicaMascota> caracteristicas){
        List<String> caracteristicasString = getCaracteristicasString(caracteristicas);
        CaracteristicaMascota preferencia;
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


    public List<String> getCaracteristicasString(List<CaracteristicaMascota> caracteristicas) {
        return caracteristicas.stream().map(CaracteristicaMascota::getNombre).collect(Collectors.toList());
    }



    public void recomendar(PublicacionIntencionDeAdopcion intencion){
        intencion.getRecomendaciones().clear(); //borrar recomendaciones viejas
        intencion.updateFechaReco();
        List<Publicacion> publisAdopcion = PublicacionService.getInstance().getDeTipo("adopcion");

        publisAdopcion.stream().forEach(adopcion -> generarRecomendacion(intencion, (PublicacionAdopcion) adopcion));
        //TODO verificar que solo se recomiende el mismo tipo de mascota?



        intencion.ordenarRecomendaciones();
    }

    private void generarRecomendacion(PublicacionIntencionDeAdopcion intencion, PublicacionAdopcion adopcion) {
        int cantPreferencias = caracteristicasCoincidentes(intencion.getPreferencias(), adopcion.getMascota().getCaracteristicas());
        int cantComodidades = caracteristicasCoincidentes(intencion.getComodidades(), adopcion.getRequerimientos());
        Recomendacion recomendacion = new Recomendacion(adopcion, cantPreferencias, cantComodidades);

        intencion.addRecomendacion(recomendacion);
    }



}

