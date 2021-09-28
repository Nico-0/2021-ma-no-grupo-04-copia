package com.dds.rescate.server;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.service.PublicacionService;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static void init(){

        PublicacionService instancia = PublicacionService.getInstance();

        Contacto contacto = new Contacto(null, null, null, null, null);

        DatosPersonales datos_1 = new DatosPersonales("pepe", null,null,null,contacto);
        DatosPersonales datos_2 = new DatosPersonales("pablo", null,null,null,contacto);
        DatosPersonales datos_3 = new DatosPersonales("cristina", null,null,null,contacto);

        UsuarioDuenio user_1 = new UsuarioDuenio("pepedestroyer9000","@$&*#HFGG445d450",datos_1);
        UsuarioDuenio user_2 = new UsuarioDuenio("pablito15","1@3$5^7*9)h",datos_2);
        UsuarioDuenio user_3 = new UsuarioDuenio("lacris","753ASADo",datos_3);

        Caracteristica animal;
        CaracteristicaMascota animalM;
        List<CaracteristicaMascota> caracteristicas = new ArrayList<>();
        animal = new Caracteristica("Color");
        animalM = new CaracteristicaMascota(animal, "si");
        CatalogoCaracteristicas.getInstance().agregarCaracteristica(animal);
        caracteristicas.add(animalM);

        Mascota perro = new Mascota(TipoMascota.PERRO, "Guay", "guauguau", "el mejor amigo", caracteristicas, Sexo.MACHO,"perro.jpg");
        Mascota gato = new Mascota(TipoMascota.GATO, "Michi", "miaumiau", "llevenselo no para de subirse a la mesa", caracteristicas, Sexo.HEMBRA, "gato.jpg");
        Mascota minipig = new Mascota(TipoMascota.MINIPIG, "CERDO", "oinkoink", "ocupa una cama de dos plazas", caracteristicas, Sexo.HEMBRA, "minipig.jpg");

        Asociacion asociacion_1 = new Asociacion("patitas al rescate",null,null,null);
        Asociacion asociacion_2 = new Asociacion("orejas rescatadas",null,null,null);


        instancia.generarPublicacionPerdida(user_1, asociacion_1, TipoMascota.GATO,"gato.jpg", EstadoEncontrada.EXCELENTE,contacto);
        instancia.generarPublicacionPerdida(user_2, asociacion_2, TipoMascota.MINIPIG,"minipig.jpg", EstadoEncontrada.BIEN,contacto);
        instancia.generarPublicacionPerdida(user_3, asociacion_1, TipoMascota.PERRO,"perro.jpg", EstadoEncontrada.MASO,contacto);
        instancia.generarPublicacionAdopcion(user_1, asociacion_2, perro);
        instancia.generarPublicacionAdopcion(user_2, asociacion_1, gato);
        instancia.generarPublicacionAdopcion(user_3, asociacion_2, minipig);

        instancia.aceptarPendientes();

    }
}
