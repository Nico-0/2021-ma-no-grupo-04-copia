package com.dds.rescate.util.validaciones;

import com.dds.rescate.exception.PasswordException;

public abstract class ValidacionContrasenia {
	protected String mensaje;

	protected ValidacionContrasenia(String mensaje){
		this.mensaje = mensaje;
	}


	public void validar(String username, String password){
		if(this.condicion(username, password))
			throw new PasswordException(mensaje);
	}

	protected abstract boolean condicion(String username, String password);
}
