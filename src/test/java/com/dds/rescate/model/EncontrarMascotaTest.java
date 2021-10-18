package com.dds.rescate.model;

import com.dds.rescate.exception.ValidadorException;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EncontrarMascotaTest {

    UsuarioDuenio pepito;
    Mascota gato;
    Contacto unPrimo;
    DatosPersonales perfilPepito;
    Notificador notificador;
    Caracteristica color;
    CaracteristicaMascota colorM;
    List<CaracteristicaMascota> caracteristicas = new ArrayList<>();
    Asociacion asociacion;

    @Before
    public void init() throws ValidadorException {
        notificador = new NotificadorSMS();
        unPrimo = new Contacto("Ceci", "lio", "a@a.com", notificador, 12345678);
        perfilPepito = new DatosPersonales("Pedro", "Lopez", new Date(), 12345678, unPrimo);
        pepito = new UsuarioDuenio("Pedro777", "contradificil", perfilPepito);
        color = new Caracteristica("Color");
        colorM = new CaracteristicaMascota(color, "naranja");
        CatalogoCaracteristicas.getInstance().agregarCaracteristica(color);
        caracteristicas.add(colorM);
        asociacion = new Asociacion(null, null, null);
        gato = new Mascota(TipoMascota.GATO, "michi", "michi", "un animal peludo de 4 patas", asociacion, caracteristicas, Sexo.MACHO, "gato.jpg");
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
