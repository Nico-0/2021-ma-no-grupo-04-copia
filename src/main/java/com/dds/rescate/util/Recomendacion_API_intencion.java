package com.dds.rescate.util;

import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.model.Recomendacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Recomendacion_API_intencion {

    public int idPublicacionIntencion;
    public Date fechaCreacion;
    public String asociacionAsignada;
    public EstadoPubli estadoPublicacion;
    public TipoMascota tipoMascota;
    //public List<CaracteristicaMascota> preferencias;
    //public List<CaracteristicaMascota> preguntas;
    public String fechaGeneracionRecomendacion;
    public int cantidadRecomendaciones;
    public List<Recomendacion_API_reco> recomendaciones;


    public Recomendacion_API_intencion(PublicacionIntencionDeAdopcion publicacion){
        this.idPublicacionIntencion = publicacion.getId();
        this.fechaCreacion = publicacion.getFechaCreacion();
        this.asociacionAsignada = publicacion.getAsociacionString();
        this.estadoPublicacion = publicacion.getEstadoPublicacion();
        this.tipoMascota = publicacion.getTipoMascota();
        //this.preferencias = publicacion.getPreferencias();
        //this.preguntas = publicacion.getPreguntas();
        this.fechaGeneracionRecomendacion = publicacion.getUltimaRecomendacion();

        List<Recomendacion> recomendaciones_ad = publicacion.getRecomendaciones();
        this.cantidadRecomendaciones = recomendaciones_ad.size();

        this.recomendaciones = new ArrayList<>();
        for (int i=0; i<cantidadRecomendaciones; i++){
            recomendaciones.add(new Recomendacion_API_reco(recomendaciones_ad.get(i)));
        }
        recomendaciones = recomendaciones.stream().sorted(Comparator.comparing(Recomendacion_API_reco::getPuntajeRecomendacion).reversed()).collect(Collectors.toList());

    }

}
