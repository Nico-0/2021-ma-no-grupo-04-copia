package com.dds.rescate.model;
import com.dds.rescate.model.Enum.TipoPregunta;

public class UsuarioAdministrador extends Usuario{

	public UsuarioAdministrador(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	public void agregarCaracteristicas (String descripcion){
		
		PreguntaCaracteristica caracteristica = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA, descripcion);
		CatalogoCaracteristicas.getInstance().agregarCaracteristica(caracteristica);
		
	}

	@Override
	public String getTipo(){
		return "administrador";
	}
}
//TODO asociar organizacion al administrador