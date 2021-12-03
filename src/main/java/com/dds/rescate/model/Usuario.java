package com.dds.rescate.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario {
	@Id
	@GeneratedValue
	public int ID;
	private String username;
	private String password;
	@ManyToOne
	public Asociacion asociacion;

	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Usuario(){

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

	public int getID(){
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

    public String getNombreCompleto() {
		return "Solo_para_usuario_comun";
    }
}
