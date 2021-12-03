package com.dds.rescate.model;
import com.dds.rescate.model.Enum.TipoPregunta;

import javax.persistence.Entity;

@Entity
public class UsuarioAdministrador extends Usuario{

	public UsuarioAdministrador(String username, String password, Asociacion asociacion) {
		super(username, password);
		//  Auto-generated constructor stub
		this.asociacion = asociacion;
	}
	private UsuarioAdministrador(){};

	public void agregarCaracteristicas (String descripcion){
		
		PreguntaCaracteristica caracteristica = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA, descripcion);
		CatalogoCaracteristicas.getInstance().agregarCaracteristica(caracteristica);
		
	}

	@Override
	public String getTipo(){
		return "administrador";
	}
}
