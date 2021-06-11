package com.dds.rescate.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;

public class RegistrarMascotaTest {
	
	UsuarioDuenio christian;
	DatosPersonales perfilChristian;
    Mascota perro;
    Contacto hermano;
    Notificador notificador;

    @Before
    /*public void init(){
    	
    	hermano = new Contacto("Leo", "Z", "z@a.com", notificador);
    	perfilChristian = new DatosPersonales("Christian", "Z",  new Date(), 123456789, hermano);
    	christian = new UsuarioDuenio("UsuarioChris", "Chris1234", perfilChristian);
        perro = new Mascota("Perro", "Jey", "Jey", "Pitbull", "Marron", Sexo.MACHO);
    }*/
    
    @Test
    public void registrarMascota(){
    	
        christian.registrarMascota(perro);
        assertEquals(1,christian.getMascotas().size());
    }

}
