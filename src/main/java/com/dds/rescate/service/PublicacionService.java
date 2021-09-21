package com.dds.rescate.service;

import com.dds.rescate.model.*;

import java.util.ArrayList;
import java.util.List;

public class PublicacionService {
    List<Publicacion> publicaciones = new ArrayList<>();
    List<Publicacion> publicacionesPendientes = new ArrayList<>();
    
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

    public List<Publicacion> mostrarPublicaciones(){
        return publicaciones;
    }
    public List<Publicacion> getPendientes(){
        return publicacionesPendientes;
    }

    public void generarPublicacion(Formulario formulario, UsuarioDuenio autor, Asociacion asociacionAsignada) {
        Publicacion publicacion = new Publicacion(autor, asociacionAsignada, Estado.PENDIENTE);
        publicacion.setDescripcion(formulario.getDescripcion());
        publicacion.setNombreRescatista(formulario.getDatosPersonales().getNombre());
        publicacion.setContactos(formulario.getDatosPersonales().getDatosContacto());
        publicacionesPendientes.add(publicacion);
    }

    public void aceptarPublicaciones() {

    }
    /*
    public void aceptarPublicacion (Publicacion publicacionNueva){
        //TODO
    }*/

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
