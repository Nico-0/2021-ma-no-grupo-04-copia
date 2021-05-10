package com.dds.rescate.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class EncontrarMascotaTest {

    UsuarioDuenio pepito;
    Mascota gato;
    Contacto unPrimo;
    Notificador notificador;

    @Before
    public void init(){
        notificador = new NotificadorSMS();
        unPrimo = new Contacto("Ceci", "lio", "a@a.com", notificador);
        pepito = new UsuarioDuenio("Pedro777", "contradificil", "Pedro", "Lopez", new Date(), "12345678", unPrimo);
        gato = new Mascota("gato", "michi", "michi", "un animal peludo de 4 patas", "pasar caracteristicas a clase", Sexo.MACHO);
        gato.perder();
    }

    @Test
    public void recuperarMascota(){
        gato.recuperar();
        Assert.assertFalse(gato.estaPerdida());
    }

    @Test
    public void notificarSMS3Veces(){

        gato.recuperarYNotificar(unPrimo, "hola pepito encontre tu mascota");
        gato.recuperarYNotificar(unPrimo, "le quedan 2 dias para venir a retirar");
        gato.recuperarYNotificar(unPrimo, "su mascota lo extrania");

        Assert.assertEquals("Error SMS", 3, NotificadorSMS.vecesNotificado);
    }

}
