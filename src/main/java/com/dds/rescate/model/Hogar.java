package com.dds.rescate.model;

import java.util.List;

public class Hogar {
	
	private String id;
	private String nombre;
	private Ubicacion ubicacion;
	private String telefono;
	private Admision admisiones;
	private int capacidad;
	private int lugares_disponibles;
	private boolean patio;
	private List<String> caracteristicas;
	
	
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Admision getAdmisiones() {
		return admisiones;
	}
	public void setAdmisiones(Admision admisiones) {
		this.admisiones = admisiones;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getLugares_disponibles() {
		return lugares_disponibles;
	}
	public void setLugares_disponibles(int lugares_disponibles) {
		this.lugares_disponibles = lugares_disponibles;
	}
	public boolean isPatio() {
		return patio;
	}
	public void setPatio(boolean patio) {
		this.patio = patio;
	}
	public List<String> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
