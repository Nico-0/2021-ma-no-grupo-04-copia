package com.dds.rescate.model;

public class Caracteristica {//Pregunta

	private String nombre;
	private String descripcion;

	public Caracteristica(String nombre) {
		this.nombre = nombre;
		this.descripcion = "⸮⸮⸮⸮⸮"+nombre+"?????";
	}

	public Caracteristica(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
