package com.dds.rescate.service;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.TipoMascota;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicacionService {
    List<Publicacion> publicaciones = new ArrayList<>();
    //List<Publicacion> publicacionesPendientes = new ArrayList<>(); //se filtran con el estado
    
    private static PublicacionService instance = null;
    private static int ID_actual;

	private PublicacionService() {
		super();
	}

	public static PublicacionService getInstance() {
		if(instance == null) {
			instance = new PublicacionService();
            ID_actual = 0;
		}
		return instance;
	}

    public List<Publicacion> getTodas(){
        return publicaciones;
    }

    public List<Publicacion> getPublicadas(){
        return publicaciones.stream().filter(Publicacion::isPublicada).collect(Collectors.toList());
    }

    public List<Publicacion> getPendientes(){
	    return publicaciones.stream().filter(Publicacion::isPendiente).collect(Collectors.toList());
    }

    public List<Publicacion> getFinalizadas(){
        return publicaciones.stream().filter(Publicacion::isFinalizada).collect(Collectors.toList());
    }

    public List<Publicacion> getDeTipo(String tipoPubli){
        return publicaciones.stream().filter(publicacion -> publicacion.getTipoPubli().equals(tipoPubli)).collect(Collectors.toList());
    }

    public Publicacion getDeID(String ID_publi){
        return publicaciones.stream().filter(publicacion -> publicacion.getIdString().equals(ID_publi)).collect(Collectors.toList()).get(0);
    }

    public void generarPublicacion(Formulario formulario, UsuarioDuenio autor, Asociacion asociacionAsignada) {
        Publicacion publicacion = new Publicacion(autor, asociacionAsignada, TipoMascota.MINIPIG);
        publicacion.setDescripcion(formulario.getDescripcion());
        publicacion.setNombreRescatista(formulario.getDatosPersonales().getNombre());
        publicacion.setContactos(formulario.getDatosPersonales().getContactos());
        publicaciones.add(publicacion);
    }

    public void reportarMascotaPerdida(Contacto contactoEnChapita){

    }

    public void agregarPublicacion(Publicacion publicacion){
        publicacion.setID(ID_actual);
        ID_actual++;
	    publicaciones.add(publicacion);
    }

    public void generarPublicacionIntencion(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota) {

        Publicacion nuevaPublicacion = new PublicacionIntencionDeAdopcion(autor, asociacionAsignada, tipoMascota, null, null);

        publicaciones.add(nuevaPublicacion);
    }


    public void aceptarPendientes() {
        getPendientes().stream().forEach(Publicacion::aceptarPublicacion);
    }

    public void aceptarPublicacion (Publicacion publicacionNueva){
        //TODO
    }

    public void confirmarMascotaEncontrada(Mascota mascotaPerdida, Formulario formulario){
        //TODO
    }
    public void quitarPublicacion(Publicacion publicacion){
        //TODO
    }


    public List<Publicacion> obtenerMascotasDisponiblesParaAdoptar(){

        List<Publicacion> publis = new ArrayList<>();

        return publis;
    }



}
