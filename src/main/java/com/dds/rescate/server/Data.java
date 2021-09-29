package com.dds.rescate.server;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.service.PublicacionService;

import java.util.ArrayList;
import java.util.Arrays;
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




        Caracteristica color = new Caracteristica("color");
        Caracteristica tamanio = new Caracteristica("tamanio");
        Caracteristica pelo = new Caracteristica("pelo");
        Caracteristica cola = new Caracteristica("cola");
        Caracteristica castrada = new Caracteristica("castrada");
        Caracteristica cantidadPatas = new Caracteristica("cantidadPatas");


        Asociacion asociacion_1 = new Asociacion("patitas al rescate",Arrays.asList(color, tamanio, pelo, cola),null,null);
        Asociacion asociacion_2 = new Asociacion("orejas rescatadas",Arrays.asList(color, castrada, cantidadPatas),null,null);

        List<CaracteristicaMascota> carac_perro = Arrays.asList(new CaracteristicaMascota(color, "marron"),
                new CaracteristicaMascota(tamanio, "grande"), new CaracteristicaMascota(pelo, "corto"), new CaracteristicaMascota(cola, "corta"));

        List<CaracteristicaMascota> carac_gato = Arrays.asList(new CaracteristicaMascota(color, "blanco"),
                new CaracteristicaMascota(castrada, "si"), new CaracteristicaMascota(cantidadPatas, "4"));

        List<CaracteristicaMascota> carac_pig = Arrays.asList(new CaracteristicaMascota(color, "rosa"),
                new CaracteristicaMascota(tamanio, "mini"), new CaracteristicaMascota(pelo, "sin"), new CaracteristicaMascota(cola, "corta"));

        Mascota perro = new Mascota(TipoMascota.PERRO, "Guay", "guauguau", "el mejor amigo", asociacion_1, carac_perro, Sexo.MACHO,"perro.jpg");
        Mascota gato = new Mascota(TipoMascota.GATO, "Michi", "miaumiau", "llevenselo no para de subirse a la mesa", asociacion_2, carac_gato, Sexo.HEMBRA, "gato.jpg");
        Mascota minipig = new Mascota(TipoMascota.MINIPIG, "CERDO", "oinkoink", "ocupa una cama de dos plazas", asociacion_1, carac_pig, Sexo.HEMBRA, "minipig.jpg");


        instancia.agregarPublicacion(new PublicacionPerdida(user_1, asociacion_1, TipoMascota.GATO, "gato.jpg", EstadoEncontrada.EXCELENTE,"",contacto));
        instancia.agregarPublicacion(new PublicacionPerdida(user_2, asociacion_2, TipoMascota.MINIPIG,"minipig.jpg", EstadoEncontrada.BIEN,"",contacto));
        instancia.agregarPublicacion(new PublicacionPerdida(user_3, asociacion_1, TipoMascota.PERRO,"perro.jpg", EstadoEncontrada.MASO,"",contacto));

        Arrays.asList("Con patio", "Otros animales", "Casa grande", "Alto presupuesto", "8hs atencion");
        Caracteristica con_patio = new Caracteristica("Con patio");
        Caracteristica otros_animales = new Caracteristica("Otros animales");
        Caracteristica casa_grande = new Caracteristica("Casa grande");
        Caracteristica alto_presupuesto = new Caracteristica("Alto presupuesto");
        Caracteristica atencion_8hs = new Caracteristica("Atencion 8hs");

        List<CaracteristicaMascota> req_1 = Arrays.asList(new CaracteristicaMascota(con_patio, "si"),
                new CaracteristicaMascota(otros_animales, "si"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "no"));
        List<CaracteristicaMascota> req_2 = Arrays.asList(new CaracteristicaMascota(con_patio, "no"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(alto_presupuesto, "no"), new CaracteristicaMascota(atencion_8hs, "si"));
        List<CaracteristicaMascota> req_3 = Arrays.asList(new CaracteristicaMascota(con_patio, "no"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "si"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "si"));

        PublicacionAdopcion adopcion_1 = new PublicacionAdopcion(user_1, asociacion_2, null, null, perro, req_1);
        PublicacionAdopcion adopcion_2 = new PublicacionAdopcion(user_2, asociacion_1, null, null, gato, req_2);
        PublicacionAdopcion adopcion_3 = new PublicacionAdopcion(user_3, asociacion_2, null, null, minipig, req_3);
        instancia.agregarPublicacion(adopcion_1);
        instancia.agregarPublicacion(adopcion_2);
        instancia.agregarPublicacion(adopcion_3);


        List<CaracteristicaMascota> cari_1 = Arrays.asList(new CaracteristicaMascota(color, "negro"), new CaracteristicaMascota(tamanio, "grande"), new CaracteristicaMascota(pelo, "sin"),
                new CaracteristicaMascota(cola, "corta"), new CaracteristicaMascota(castrada, "si"), new CaracteristicaMascota(cantidadPatas, "4"));

        List<CaracteristicaMascota> cari_2 = Arrays.asList(new CaracteristicaMascota(color, "rosa"), new CaracteristicaMascota(tamanio, "mediana"), new CaracteristicaMascota(pelo, "largo"),
                new CaracteristicaMascota(cola, "corta"), new CaracteristicaMascota(castrada, "no"), new CaracteristicaMascota(cantidadPatas, "4"));

        List<CaracteristicaMascota> cari_3 = Arrays.asList(new CaracteristicaMascota(color, "marron"), new CaracteristicaMascota(tamanio, "chica"), new CaracteristicaMascota(pelo, "corto"),
                new CaracteristicaMascota(cola, "larga"), new CaracteristicaMascota(castrada, "si"), new CaracteristicaMascota(cantidadPatas, "4"));


        List<CaracteristicaMascota> comodidad_1 = Arrays.asList(new CaracteristicaMascota(con_patio, "no"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "si"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "no"));
        List<CaracteristicaMascota> comodidad_2 = Arrays.asList(new CaracteristicaMascota(con_patio, "no"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(alto_presupuesto, "no"), new CaracteristicaMascota(atencion_8hs, "si"));
        List<CaracteristicaMascota> comodidad_3 = Arrays.asList(new CaracteristicaMascota(con_patio, "si"),
                new CaracteristicaMascota(otros_animales, "si"), new CaracteristicaMascota(casa_grande, "si"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "si"));

        PublicacionIntencionDeAdopcion intencion_1 = new PublicacionIntencionDeAdopcion(user_1, asociacion_1, TipoMascota.GATO, cari_1, comodidad_1);
        PublicacionIntencionDeAdopcion intencion_2 = new PublicacionIntencionDeAdopcion(user_2, asociacion_2, TipoMascota.PERRO, cari_2, comodidad_2);
        PublicacionIntencionDeAdopcion intencion_3 = new PublicacionIntencionDeAdopcion(user_3, asociacion_1, TipoMascota.MINIPIG, cari_3, comodidad_3);


        instancia.agregarPublicacion(intencion_1);
        instancia.agregarPublicacion(intencion_2);
        instancia.agregarPublicacion(intencion_3);






        instancia.aceptarPendientes();

    }
}
