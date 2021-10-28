package com.dds.rescate.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CaracteristicaMascota { //PreguntaMascota

	@Id
	@GeneratedValue
	public int ID;

	@Embedded
	private Caracteristica caracteristica;

	private String valor;
	
	public CaracteristicaMascota(Caracteristica caracteristica, String respuesta) {
		this.caracteristica = caracteristica;
		this.valor = respuesta;
	}
	private CaracteristicaMascota(){}

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
