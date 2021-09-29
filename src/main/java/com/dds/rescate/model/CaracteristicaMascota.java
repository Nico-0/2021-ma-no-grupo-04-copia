package com.dds.rescate.model;

public class CaracteristicaMascota {

	private Caracteristica caracteristica;
	private String valor;
	
	public CaracteristicaMascota(Caracteristica caracteristica, String valor) {
		this.caracteristica = caracteristica;
		this.valor = valor;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getNombre(){
		return getCaracteristica().getNombre();
	}

}
