package com.dds.rescate.model;

import javax.persistence.*;

@Entity
public class Respuesta {

	@Id
	@GeneratedValue
	public int ID;

	@OneToOne
	private PreguntaCaracteristica preg_carac;

	private String valorRespuesta;
	
	public Respuesta(PreguntaCaracteristica caracteristica, String respuesta) {
		this.preg_carac = caracteristica;
		this.valorRespuesta = respuesta;
	}
	private Respuesta(){}

	public PreguntaCaracteristica getCaracteristica() {
		return preg_carac;
	}

	public void setCaracteristica(PreguntaCaracteristica caracteristica) {
		this.preg_carac = caracteristica;
	}

	public String getValor() {
		return valorRespuesta;
	}

	public void setValor(String valor) {
		this.valorRespuesta = valor;
	}
	
	public String getNombre(){
		return getCaracteristica().getNombre();
	}

}
