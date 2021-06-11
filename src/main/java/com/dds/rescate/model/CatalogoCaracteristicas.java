package com.dds.rescate.model;

import java.util.List;

public class CatalogoCaracteristicas {

	private List<Caracteristica> caracteristicas;
	private static CatalogoCaracteristicas instance;
	
	public static CatalogoCaracteristicas getInstance() {
		if(instance == null) {
			instance = new CatalogoCaracteristicas();
		}
		return instance;
	}
	
	private CatalogoCaracteristicas() {
		// TODO Auto-generated constructor stub
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public void agregarCaracteristica(Caracteristica caracteristica){
		caracteristicas.add(caracteristica);
	}

}
