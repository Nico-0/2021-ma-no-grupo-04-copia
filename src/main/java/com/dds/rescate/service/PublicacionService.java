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
    
    private static PublicacionService instance;

	
	private PublicacionService() {
		super();
	}

	public static PublicacionService getInstance() {
		if(instance == null) {
			instance = new PublicacionService();
		}
		return instance;
	}

    public List<Publicacion> getPublicadas(){
        return publicaciones.stream().filter(publicacion -> !publicacion.isPendiente()).collect(Collectors.toList());
    }

    public List<Publicacion> getPendientes(){
	    return publicaciones.stream().filter(Publicacion::isPendiente).collect(Collectors.toList());
    }

    public void generarPublicacion(Formulario formulario, UsuarioDuenio autor, Asociacion asociacionAsignada) {
        Publicacion publicacion = new Publicacion(autor, asociacionAsignada, TipoMascota.MINIPIG);
        publicacion.setDescripcion(formulario.getDescripcion());
        publicacion.setNombreRescatista(formulario.getDatosPersonales().getNombre());
        publicacion.setContactos(formulario.getDatosPersonales().getDatosContacto());
        publicaciones.add(publicacion);
    }

    public void reportarMascotaPerdida(Contacto contactoEnChapita){

    }

    public void generarPublicacionPerdida(UsuarioDuenio autor, Asociacion asociacionAsignada, TipoMascota tipoMascota, String nombreFoto, EstadoEncontrada estadoEncontrada, Contacto contactoMinimo) {

	    //TODO se elige el contacto o se saca del usuario rescatista?
        Publicacion nuevaPublicacion = new PublicacionPerdida(autor, asociacionAsignada, tipoMascota, nombreFoto, estadoEncontrada,"",contactoMinimo);

        publicaciones.add(nuevaPublicacion);
    }

    public void generarPublicacionAdopcion(UsuarioDuenio autor, Asociacion asociacionAsignada, Mascota mascota) {

        Publicacion nuevaPublicacion = new PublicacionAdopcion(autor, asociacionAsignada, null, null, mascota);

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

    public void generarInteresAdopcion(String descripcion){
        //TODO
    }
    public List<Publicacion> obtenerMascotasDisponiblesParaAdoptar(){

        List<Publicacion> publis = new ArrayList<>();

        return publis;
    }



}
