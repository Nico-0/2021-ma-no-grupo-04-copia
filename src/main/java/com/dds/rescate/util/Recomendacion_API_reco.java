package com.dds.rescate.util;

import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.model.PublicacionAdopcion;
import com.dds.rescate.model.Recomendacion;

public class Recomendacion_API_reco {

    public int puntajeRecomendacion;

    public String duenioMascota;
    public String nombreMascota;
    public TipoMascota tipoMascota;
    public String asociacionMascota;
    public String asociacionPublicacion;

    public Recomendacion_API_reco(Recomendacion recomendacion){
        this.puntajeRecomendacion = recomendacion.getTotalInt();
        PublicacionAdopcion adopcion = recomendacion.getAdopcion();
        this.duenioMascota = adopcion.getAutorString();
        this.nombreMascota = adopcion.getNombreMascota();
        this.tipoMascota = adopcion.getMascota().getTipoMascota();
        this.asociacionMascota = adopcion.getMascota().getNombreAsociacionString();
        this.asociacionPublicacion = adopcion.getAsociacionString();
    }

    public int getPuntajeRecomendacion(){
        return puntajeRecomendacion;
    }

}
