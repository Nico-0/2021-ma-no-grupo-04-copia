package com.dds.rescate.util;

import com.dds.rescate.model.Publicacion;
import com.dds.rescate.model.PublicacionIntencionDeAdopcion;
import com.dds.rescate.model.UsuarioDuenio;
import com.dds.rescate.service.MongoDB;
import com.dds.rescate.service.PublicacionService;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Entity("recomendaciones")
public class Recomendacion_API {

    @Id
    int id;
    int id_user;
    String nombreUsuario;
    String nombreAutor;
    int cantidadIntencionesAdopcion;
    @Embedded
    List<Recomendacion_API_intencion> interesesAdopcion;

    public Recomendacion_API(UsuarioDuenio usuario, EntityManager em){
        this.id = MongoDB.getInstance().asignarID();
        this.id_user = usuario.getID();
        this.nombreUsuario = usuario.getUsername();
        this.nombreAutor = usuario.getNombre();

        PublicacionService repo = new PublicacionService(em);
        List<Publicacion> intenciones = repo.getIntencionesPublicadas(usuario.getUsername());
        this.cantidadIntencionesAdopcion = intenciones.size();

        this.interesesAdopcion = new ArrayList<>();
        for (int i=0; i<cantidadIntencionesAdopcion; i++){
            interesesAdopcion.add(new Recomendacion_API_intencion((PublicacionIntencionDeAdopcion) intenciones.get(i)));
        }
    }

    private Recomendacion_API(){}

}
