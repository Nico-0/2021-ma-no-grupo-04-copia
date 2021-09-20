package com.dds.rescate.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RegistrarMascotaTest {
	
	UsuarioDuenio christian;
	DatosPersonales perfilChristian;
    Mascota perro;
    Contacto hermano;
    Notificador notificador;
    Caracteristica color;
    CaracteristicaMascota colorM;
    List<CaracteristicaMascota> caracteristicas = new ArrayList<>();

    @Before
    public void init() throws Exception {
    	
    	hermano = new Contacto("Leo", "Z", "z@a.com", notificador, 800333);
    	perfilChristian = new DatosPersonales("Christian", "Z",  new Date(), 123456789, hermano);
    	christian = new UsuarioDuenio("UsuarioChris", "Chris1234", perfilChristian);

        color = new Caracteristica("Color");
        colorM = new CaracteristicaMascota(color, "marron");
        CatalogoCaracteristicas.getInstance().agregarCaracteristica(color);
        caracteristicas.add(colorM);
        perro = new Mascota("Perro", "Jey", "Jey", "Pitbull", caracteristicas, Sexo.MACHO);
    }
    
    @Test
    public void registrarMascota(){
    	
        christian.registrarMascota(perro);
        assertEquals(1,christian.getMascotas().size());
    }

}
