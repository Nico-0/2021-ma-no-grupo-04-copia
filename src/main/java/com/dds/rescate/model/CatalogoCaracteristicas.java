package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

public class CatalogoCaracteristicas {  //no se llegó a usar

	private List<PreguntaCaracteristica> caracteristicas= new ArrayList<>();
	private static CatalogoCaracteristicas instance;
	
	public static CatalogoCaracteristicas getInstance() {
		if(instance == null) {
			instance = new CatalogoCaracteristicas();
		}
		return instance;
	}
	
	private CatalogoCaracteristicas() {
		//  Auto-generated constructor stub
	}

	public List<PreguntaCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<PreguntaCaracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public void agregarCaracteristica(PreguntaCaracteristica caracteristica){
		caracteristicas.add(caracteristica);
	}

}
