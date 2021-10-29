package com.dds.rescate.model;

import com.dds.rescate.model.Enum.TipoPregunta;

import javax.persistence.*;


@Entity
public class PreguntaCaracteristica {
	@Id
	@GeneratedValue
	public int ID;

	private String nombre;
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private TipoPregunta tipo;

	private PreguntaCaracteristica() { //constructor vacio para hibernate
	}
	public PreguntaCaracteristica(TipoPregunta tipo, String nombre) {
		this.nombre = nombre;
		//this.descripcion = "⸮⸮⸮⸮⸮"+nombre+"?????";
		this.tipo = tipo;
	}

	public PreguntaCaracteristica(TipoPregunta tipo, String nombre, String descripcion) {
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
