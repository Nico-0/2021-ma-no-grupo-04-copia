package com.dds.rescate.model;

public class Ubicacion {
	
	private String direccion;
	private float lat;
	private float _long;
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float get_long() {
		return _long;
	}
	public void set_long(float _long) {
		this._long = _long;
	}
}
