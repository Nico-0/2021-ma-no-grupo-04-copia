package com.dds.rescate.util;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.PublicacionService;

import java.util.ArrayList;
import java.util.List;

public class Recomendacion_API {

    int id_user;
    String nombreUsuario;
    String nombreAutor;
    int cantidadIntencionesAdopcion;
    List<Recomendacion_API_intencion> interesesAdopcion;

    public Recomendacion_API(UsuarioDuenio usuario){
        this.id_user = usuario.getID();
        this.nombreUsuario = usuario.getUsername();
        this.nombreAutor = usuario.getNombre();

        List<Publicacion> intenciones = PublicacionService.getInstance().getIntecionesByUserID(usuario.getID());
        this.cantidadIntencionesAdopcion = intenciones.size();

        this.interesesAdopcion = new ArrayList<>();
        for (int i=0; i<cantidadIntencionesAdopcion; i++){
            interesesAdopcion.add(new Recomendacion_API_intencion((PublicacionIntencionDeAdopcion) intenciones.get(i)));
        }
    }

}
