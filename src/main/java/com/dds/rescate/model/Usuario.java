package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String username;
	private String password;

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

	public String getTipo(){
		return "usuario";
	}
	
}
