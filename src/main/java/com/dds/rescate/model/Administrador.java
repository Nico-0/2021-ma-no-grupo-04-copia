package com.dds.rescate.model;
import com.dds.rescate.model.Mascota.*;

public class Administrador extends Usuario{

	public Administrador(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	public void agregarCaracteristicas (String descripcion){
		
		Caracteristica caracteristica = new Caracteristica(descripcion);
		CatalogoCaracteristicas.getInstance().agregarCaracteristica(caracteristica);
		
	}
	
}
