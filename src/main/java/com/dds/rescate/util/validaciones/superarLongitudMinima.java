package com.dds.rescate.util.validaciones;

public class superarLongitudMinima extends ValidacionContrasenia {

	public superarLongitudMinima(){
		super("Inutilizable: no cumple con el minimo de caracteres!!");
	}

	@Override
	public boolean condicion(String username, String password) {
		return password.length() < LONGITUD_MINIMA;
	}
}
