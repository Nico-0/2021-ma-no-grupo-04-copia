package com.dds.rescate.util;

import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.model.PublicacionAdopcion;
import com.dds.rescate.model.Recomendacion;
import com.dds.rescate.service.MongoDB;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("reco")
public class Recomendacion_API_reco {

    @Id
    public int id;

    public int puntajeRecomendacion;

    public String duenioMascota;
    public String nombreMascota;
    public TipoMascota tipoMascota;
    public String asociacionMascota;
    public String asociacionPublicacion;

    public Recomendacion_API_reco(Recomendacion recomendacion){
        this.id = MongoDB.getInstance().asignarID();
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

    private Recomendacion_API_reco(){}
}
