package com.dds.rescate.service;

import com.dds.rescate.model.Estado;
import com.dds.rescate.model.Formulario;
import com.dds.rescate.model.Publicacion;

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

    public void generarPublicacion(Formulario formulario) {
        Publicacion publicacion = new Publicacion();
        publicacion.setDescripcion(formulario.getDescripcion());
        publicacion.setNombreRescatista(formulario.getDatosPersonales().getNombre());
        publicacion.setContactos(formulario.getDatosPersonales().getDatosContacto());
        publicacion.setEstado(Estado.PENDIENTE);
        publicacionesPendientes.add(publicacion);
    }

    public void aceptarPublicaciones() {

    }
}
