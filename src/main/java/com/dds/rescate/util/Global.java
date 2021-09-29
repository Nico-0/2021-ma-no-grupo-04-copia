package com.dds.rescate.util;

import com.dds.rescate.model.CaracteristicaMascota;
import com.dds.rescate.service.Recomendador;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Global {
    //En lugar de clase global se podrian guardar en archivo de configuracion

    private static List<String> requerimientos = Arrays.asList("Con patio", "Otros animales", "Casa grande", "Alto presupuesto", "Atencion 8hs");

    private static List<String> preguntasGenerales = Arrays.asList("Usted dueño fue buena persona?", "Cuantas vacunas se dio la mascota?"); //para responder en el detalle de una publicacion en adopcion

    private static Global instance;

    public static Global getInstance() {
        if(instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public List<String> getRequerimientos(){
        return requerimientos;
    }

    public List<String> getPreguntasGenerales(){
        return preguntasGenerales;
    }

    public void validarRequerimientosGenerales(List<CaracteristicaMascota> requerimientosDados){
        if(requerimientos != null) {
            List<String> preferenciasPublicaciones = getPreferenciasString(requerimientosDados);
            Boolean contieneTodas = preferenciasPublicaciones.containsAll(requerimientos);
            if (!contieneTodas)
                throw new RuntimeException("No se completaron todos los requerimientos generales");
        }
    }

    public List<String> getPreferenciasString(List<CaracteristicaMascota> requerimientosDados){
        return requerimientosDados.stream().map(caracteristica -> caracteristica.getCaracteristica().getNombre()).collect(Collectors.toList());
    }

}
