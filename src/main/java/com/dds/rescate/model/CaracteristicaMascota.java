package com.dds.rescate.model;

public class CaracteristicaMascota {

	private Caracteristica caracteristica;
	private Object valor;
	
	public CaracteristicaMascota(Caracteristica caracteristica, Object valor) {
		this.caracteristica = caracteristica;
		this.valor = valor;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	

}
