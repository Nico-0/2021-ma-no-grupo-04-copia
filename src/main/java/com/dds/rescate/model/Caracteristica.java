package com.dds.rescate.model;

import com.dds.rescate.model.Enum.TipoPregunta;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class Caracteristica {//Pregunta

	private String nombre;
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private TipoPregunta tipo;

	public Caracteristica() { //constructor vacio para hibernate
	}
	public Caracteristica(TipoPregunta tipo, String nombre) {
		this.nombre = nombre;
		//this.descripcion = "⸮⸮⸮⸮⸮"+nombre+"?????";
		this.tipo = tipo;
	}

	public Caracteristica(TipoPregunta tipo, String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
