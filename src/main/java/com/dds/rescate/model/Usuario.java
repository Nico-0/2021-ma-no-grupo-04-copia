package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String username;
	private String password;
	private List<Mascota> mascotas = new ArrayList<>();
	
	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}
	
	public void registrarMascota(Mascota mascota) {
		this.mascotas.add(mascota);
	}
	
}
