package com.dds.rescate.service;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.EstadoPubli;
import com.dds.rescate.model.Enum.TipoMascota;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PublicacionService {

	private PublicacionService() {
		super();
	}

    private EntityManager entityManager;

    public PublicacionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Publicacion> getTodas(){
        return entityManager.createQuery("from Publicacion", Publicacion.class).getResultList();
    }

    public List<Publicacion> getPublicadas(){
        //return publicaciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());
        return entityManager.createQuery("from Publicacion p where p.estadoPublicacion = ?1", Publicacion.class)
                .setParameter(1, EstadoPubli.PUBLICADA)
                .getResultList();
    }

    public List<Publicacion> getPendientes(){
	    //return publicaciones.stream().filter(Publicacion::isPendiente).collect(Collectors.toList());
       return entityManager.createQuery("from Publicacion p where p.estadoPublicacion = ?1", Publicacion.class)
                .setParameter(1, EstadoPubli.PENDIENTE)
                .getResultList();
    }

    public List<Publicacion> getFinalizadas(){
        return entityManager.createQuery("from Publicacion p where p.estadoPublicacion = ?1", Publicacion.class)
                .setParameter(1, EstadoPubli.FINALIZADA)
                .getResultList();
    }

    public List<Publicacion> getDeTipo(String tipoPubli){
      /*  switch (tipoPubli){
            case "adopcion":
                return entityManager.createQuery("from PublicacionAdopcion p where p.estadoPublicacion = ?1", Publicacion.class)
                        .setParameter(1, EstadoPubli.PUBLICADA)
                        .getResultList();
            case "perdida":
                return entityManager.createQuery("from PublicacionPerdida p where p.estadoPublicacion = ?1", Publicacion.class)
                        .setParameter(1, EstadoPubli.PUBLICADA)
                        .getResultList();
            case "intencion":
                return entityManager.createQuery("from PublicacionIntencionDeAdopcion p where p.estadoPublicacion = ?1", Publicacion.class)
                        .setParameter(1, EstadoPubli.PUBLICADA)
                        .getResultList();
            default:
                return null;
        }*/
        return getPublicadas().stream().filter(publicacion -> publicacion.getTipoPubli().equals(tipoPubli)).collect(Collectors.toList());
    }

    public List<Publicacion> getPublicacionesBy(UsuarioDuenio usuario, EstadoPubli estado){
        List<Publicacion> publicaciones = getPublicacionesByUser(usuario);
        publicaciones = publicaciones.stream().filter(p -> !p.getTipoPubli().equals("intencion")).collect(Collectors.toList());
        return publicaciones.stream().filter(publicacion -> publicacion.getEstadoPublicacion().equals(estado)).collect(Collectors.toList());
    }

    public List<Publicacion> getPublicacionesByUser(UsuarioDuenio usuario){
        return getTodas().stream().filter(publicacion -> publicacion.getAutor().equals(usuario)).collect(Collectors.toList());
    }

    public Publicacion getDeID(String ID_publi){
        return entityManager.createQuery("from Publicacion p where p.id = ?1", Publicacion.class).setParameter(1, Integer.valueOf(ID_publi)).getSingleResult();
    }

    public List<Publicacion> getIntecionesByUserID(int id_user){
        return getDeTipo("intencion").stream().filter(p -> p.getAutor().getID() == id_user).collect(Collectors.toList());
    }

    public void aceptarPendientes() {
        getPendientes().stream().forEach(Publicacion::aceptarPublicacion);
    }

    public List<Publicacion> getIntencionesPublicadas(String username){
        List<Publicacion> intenciones = getDeTipo("intencion");
        return intenciones.stream().filter(Publicacion::isPublicada).
                filter(p -> p.getAutor().getUsername().equals(username)).collect(Collectors.toList());
    }

    public List<Publicacion> getIntencionesPublicadas(){
        List<Publicacion> intenciones = getDeTipo("intencion");
        return intenciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());
    }









//---------------------------------------------------------------------

    private static int ID_actual;
    List<Publicacion> publicaciones = new ArrayList<>();
    private static PublicacionService instance = null;
    public static PublicacionService getInstance() {
        if(instance == null) {
            instance = new PublicacionService();
            ID_actual = 0;
        }
        return instance;
    }
    public void agregarPublicacion(Publicacion publicacion){
        publicacion.setID(ID_actual);
        ID_actual++;
        publicaciones.add(publicacion);
    }
}
