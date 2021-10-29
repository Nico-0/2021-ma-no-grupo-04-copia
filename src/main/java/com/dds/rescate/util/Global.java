package com.dds.rescate.util;

import com.dds.rescate.model.Respuesta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Global {
    //En lugar de clase global se podrian guardar en archivo de configuracion

    private static List<String> preguntasGenerales = Arrays.asList("Con patio", "Otros animales", "Casa grande");

    private static Global instance;

    public static Global getInstance() {
        if(instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public List<String> getPreguntasGenerales(){
        return preguntasGenerales;
    }

    public void validarRequerimientosGenerales(List<Respuesta> requerimientosDados){
        if(preguntasGenerales != null) {
            List<String> preferenciasPublicaciones = getPreferenciasString(requerimientosDados);
            Boolean contieneTodas = preferenciasPublicaciones.containsAll(preguntasGenerales);
            if (!contieneTodas)
                throw new RuntimeException("No se completaron todos los requerimientos generales");
        }
    }

    public List<String> getPreferenciasString(List<Respuesta> requerimientosDados){
        return requerimientosDados.stream().map(caracteristica -> caracteristica.getCaracteristica().getNombre()).collect(Collectors.toList());
    }

}
