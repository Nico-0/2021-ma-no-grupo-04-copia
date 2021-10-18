package com.dds.rescate.server;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;

import java.util.*;

public class Data {
    public static void init(){

        PublicacionService instancia = PublicacionService.getInstance();

        NotificadorEmail notiEmail = new NotificadorEmail();
        NotificadorEmail notiEmail2 = new NotificadorEmail();

//Generar usuarios

        Contacto contacto = new Contacto("Lucas", "sin apellido", "🙌@✌.com", notiEmail, 800);
        Contacto contacto2 = new Contacto("Primero", "Lunes", "cualquiera@gmail.com", notiEmail , 1185855858);
        Contacto contacto3 = new Contacto("Segundo", "Martes", "cualquiera2@gmail.com", notiEmail2 , 1165565654);

        DatosPersonales datos_1 = new DatosPersonales("pepe", null,null,null,contacto);
        DatosPersonales datos_2 = new DatosPersonales("pablo", null,null,null,contacto);
        DatosPersonales datos_3 = new DatosPersonales("cristina", null ,null,null,contacto);
        DatosPersonales datos_4 = new DatosPersonales("Lunes", "Testing" , new Date( 96, 9, 19),12345678,contacto2);
        DatosPersonales datos_5 = new DatosPersonales("Martes", "Testing" , new Date( 05, 2, 15),87654321,contacto3);

        UsuarioDuenio user_1 = new UsuarioDuenio("pepedestroyer9000","@$&*#HFGG445d450",datos_1);
        UsuarioDuenio user_2 = new UsuarioDuenio("pablito15","1@3$5^7*9)h",datos_2);
        UsuarioDuenio user_3 = new UsuarioDuenio("lacris","753ASADo",datos_3);
        //Usuarios en repo
        UsuarioDuenio user_4 = new UsuarioDuenio("Usuario1","Usuario1",datos_4);
        UsuarioDuenio user_5 = new UsuarioDuenio("LunesTrece","Contrasenia",datos_5);
        //Test usuario guardado
        GeneradorUsuario repositorio = GeneradorUsuario.getInstance();
        repositorio.agregarUsuario(user_4);
        repositorio.agregarUsuario(user_5);


//Generar asociaciones

        //generar caracteristicas
        Caracteristica color = new Caracteristica("color");
        Caracteristica tamanio = new Caracteristica("tamanio");
        Caracteristica pelo = new Caracteristica("pelo");
        Caracteristica cola = new Caracteristica("cola");
        Caracteristica castrada = new Caracteristica("castrada");
        Caracteristica cantidadPatas = new Caracteristica("cantidadPatas");

        //generar valor para cada combinacion
        //las combinaciones no registradas dan 0
        //no registrada pero respuestas iguales dan 10
        //Comparacion blanco_blanco_1 = new Comparacion("blanco","blanco",10);
        Comparacion blanco_rosa_1 = new Comparacion("blanco","rosa",5);
        Comparacion negro_marron_1 = new Comparacion("negro","marron",5);
        List<Comparacion> valoresRespuestaColor = Arrays.asList(blanco_rosa_1, negro_marron_1);

        Comparacion grande_mediano_1 = new Comparacion("grande","mediano",5);
        Comparacion mediano_peque_1 = new Comparacion("mediano","peque",5);
        List<Comparacion> valoresRespuestaTamanio_1 = Arrays.asList(grande_mediano_1, mediano_peque_1);

        Comparacion largo_corto_1 = new Comparacion("largo","corto",5);
        Comparacion corto_sin_1 = new Comparacion("corto","sin",5);
        List<Comparacion> valoresRespuestaPelo_1 = Arrays.asList(largo_corto_1, corto_sin_1);

        List<Comparacion> valoresRespuestaCola_1 = Collections.emptyList();

        //generar las caracteristicas/preguntas
        Pregunta caracteristicaColor = new Pregunta(color, Arrays.asList("blanco", "negro", "marron", "rosa"), valoresRespuestaColor);
        Pregunta caracteristicaTamanio = new Pregunta(tamanio, Arrays.asList("grande", "mediano", "peque"), valoresRespuestaTamanio_1);
        Pregunta caracteristicaPelo = new Pregunta(pelo, Arrays.asList("largo", "corto", "sin"), valoresRespuestaPelo_1);
        Pregunta caracteristicaCola = new Pregunta(cola, Arrays.asList("larga", "corta"), valoresRespuestaCola_1);
        List<Pregunta> caracteristicas_1 = Arrays.asList(caracteristicaColor, caracteristicaTamanio, caracteristicaPelo, caracteristicaCola);


        //para asociacion 2
        Comparacion indistinto_si_1 = new Comparacion("indistinto","si",10);
        Comparacion indistinto_no_1 = new Comparacion("indistinto","no",10);
        List<Comparacion> valoresRespuestaCastrada_1 = Arrays.asList(indistinto_si_1, indistinto_no_1);

        Comparacion dos_tres_1 = new Comparacion("dos","tres",5);
        Comparacion cuatro_tres_1 = new Comparacion("cuatro","tres",5);
        List<Comparacion> valoresRespuestaPatas_1 = Arrays.asList(dos_tres_1, cuatro_tres_1);

        Pregunta caracteristicaCastrada = new Pregunta(castrada, Arrays.asList("si", "no", "indistinto"), valoresRespuestaCastrada_1);
        Pregunta caracteristicaPatas = new Pregunta(cantidadPatas, Arrays.asList("dos", "tres", "cuatro"), valoresRespuestaPatas_1);
        List<Pregunta> caracteristicas_2 = Arrays.asList(caracteristicaColor, caracteristicaCastrada, caracteristicaPatas);


        //generar preguntas generales
        Caracteristica con_patio = new Caracteristica("Con patio", "⸮Cuenta con un patio enorme?");
        Caracteristica otros_animales = new Caracteristica("Otros animales", "cuenta o no con otros animales");
        Caracteristica casa_grande = new Caracteristica("Casa grande");

        Comparacion patio_grande_chico_1 = new Comparacion("grande","chico",5);
        Comparacion patio_chico_no_1 = new Comparacion("chico","no",5);
        List<Comparacion> valoresRespuestaPatio_1 = Arrays.asList(patio_grande_chico_1, patio_chico_no_1);

        List<Comparacion> valoresRespuestaOtrosAnimales_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaCasaGrande_1 = Collections.emptyList();

        Pregunta preguntaPatio = new Pregunta(con_patio, Arrays.asList("grande", "chico", "no"), valoresRespuestaPatio_1);
        Pregunta preguntaOtrosAnimales = new Pregunta(otros_animales, Arrays.asList("si", "no"), valoresRespuestaOtrosAnimales_1);
        Pregunta preguntaCasaGrande = new Pregunta(casa_grande, Arrays.asList("si", "no"), valoresRespuestaCasaGrande_1);


        //generar preguntas
        Caracteristica alto_presupuesto = new Caracteristica("Alto presupuesto");
        Caracteristica atencion_8hs = new Caracteristica("Atencion 8hs");
        Caracteristica duenio_buena_persona = new Caracteristica("Duenio buena persona", "Usted dueño fue buena persona?");
        Caracteristica vacunada = new Caracteristica("Vacunada");

        Comparacion atencion_10min_1h_1 = new Comparacion("10min","1h",5);
        Comparacion atencion_1h_8hs_1 = new Comparacion("1h","8hs",5);
        List<Comparacion> valoresRespuestaAtencion_1 = Arrays.asList(atencion_10min_1h_1, atencion_1h_8hs_1);

        List<Comparacion> valoresRespuestaAltoPresupuesto_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaBuenaPersona_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaVacunada_1 = Collections.emptyList();

        Pregunta preguntaAltoPresupuesto = new Pregunta(alto_presupuesto, Arrays.asList("si", "no"), valoresRespuestaAltoPresupuesto_1);
        Pregunta preguntaAtencion = new Pregunta(atencion_8hs, Arrays.asList("10min", "1h", "8hs"), valoresRespuestaAtencion_1);
        Pregunta preguntaBuenaPersona = new Pregunta(duenio_buena_persona, Arrays.asList("si", "no"), valoresRespuestaBuenaPersona_1);
        Pregunta preguntaVacunada = new Pregunta(vacunada, Arrays.asList("si", "no"), valoresRespuestaVacunada_1);


        List<Pregunta> preguntas_1 = Arrays.asList(preguntaPatio, preguntaOtrosAnimales, preguntaCasaGrande, preguntaAltoPresupuesto, preguntaAtencion);
        List<Pregunta> preguntas_2 = Arrays.asList(preguntaPatio, preguntaOtrosAnimales, preguntaCasaGrande, preguntaBuenaPersona, preguntaVacunada);

        //y por ultimo estas dos lineas
        Asociacion asociacion_1 = new Asociacion("patitas al rescate",caracteristicas_1,preguntas_1);
        Asociacion asociacion_2 = new Asociacion("orejas rescatadas",caracteristicas_2,preguntas_2);


//Generar mascotas

        //deben contener las caracteristicas definidas por su asociacion y ademas las respuestas definidas
        //asociacion 1 --> color, tamanio, pelo, cola
        //color -> "blanco", "negro", "marron", "rosa"
        //tamanio -> "grande", "mediano", "peque"
        //pelo -> "largo", "corto", "sin"
        //cola -> "larga", "corta"

        //asociacion 2 --> color, castrada, cantidadPatas
        //castrada -> "si", "no", "indistinto"
        //cantidadPatas -> "dos", "tres", "cuatro"

        List<CaracteristicaMascota> carac_perro = Arrays.asList(new CaracteristicaMascota(color, "marron"),
                new CaracteristicaMascota(tamanio, "grande"), new CaracteristicaMascota(pelo, "largo"), new CaracteristicaMascota(cola, "corta"));

        List<CaracteristicaMascota> carac_perro_chico = Arrays.asList(new CaracteristicaMascota(color, "negro"),
                new CaracteristicaMascota(tamanio, "peque"), new CaracteristicaMascota(pelo, "corto"), new CaracteristicaMascota(cola, "larga"));

        List<CaracteristicaMascota> carac_gato = Arrays.asList(new CaracteristicaMascota(color, "blanco"),
                new CaracteristicaMascota(castrada, "si"), new CaracteristicaMascota(cantidadPatas, "cuatro"));

        List<CaracteristicaMascota> carac_pig = Arrays.asList(new CaracteristicaMascota(color, "rosa"),
                new CaracteristicaMascota(tamanio, "mediano"), new CaracteristicaMascota(pelo, "sin"), new CaracteristicaMascota(cola, "corta"));

        List<CaracteristicaMascota> carac_cerdo = Arrays.asList(new CaracteristicaMascota(color, "blanco"),
                new CaracteristicaMascota(castrada, "indistinto"), new CaracteristicaMascota(cantidadPatas, "tres"));

        Mascota perro = new Mascota(TipoMascota.PERRO, "Guay", "guauguau", "el mejor amigo", asociacion_1, carac_perro, Sexo.MACHO,"perro.jpg");
        Mascota gato = new Mascota(TipoMascota.GATO, "Michi", "miaumiau", "llevenselo no para de subirse a la mesa", asociacion_2, carac_gato, Sexo.HEMBRA, "gato.jpg");
        Mascota minipig = new Mascota(TipoMascota.MINIPIG, "CERDO", "oinkoink", "ocupa una cama de dos plazas", asociacion_1, carac_pig, Sexo.HEMBRA, "minipig.jpg");
        Mascota perro2 = new Mascota(TipoMascota.PERRO, "Pedro mini", "Caniche", "demasiado chico", asociacion_1, carac_perro_chico, Sexo.MACHO,"perroCaniche.jpg");
        Mascota cerdo = new Mascota(TipoMascota.MINIPIG, "George", "dinosaurio", "salta en charcos de lodo", asociacion_2, carac_cerdo, Sexo.MACHO, "minipig.jpg");

        //Agrego mascota a un Usuario(el que esta en el repo)
        user_4.agregarMascota(perro);
        user_5.agregarMascota(gato);
        user_4.agregarMascota(minipig);
        user_4.agregarMascota(perro2);
        user_4.agregarMascota(cerdo);


//Generar publicaciones

        //de mascota perdida
        instancia.agregarPublicacion(new PublicacionPerdida(user_1, asociacion_1, TipoMascota.GATO, "gato.jpg", EstadoEncontrada.EXCELENTE,"",contacto));
        instancia.agregarPublicacion(new PublicacionPerdida(user_2, asociacion_2, TipoMascota.MINIPIG,"minipig.jpg", EstadoEncontrada.BIEN,"",contacto));
        instancia.agregarPublicacion(new PublicacionPerdida(user_3, asociacion_1, TipoMascota.PERRO,"perro.jpg", EstadoEncontrada.MASO,"",contacto));


        //de adopcion
        //no esta puesta la restriccion de publicar en la misma asociacion que la mascota este registrada
        //deben contener las preguntas definidas por su asociacion y ademas las respuestas definidas
        //asociacion 1 --> con_patio, otros_animales, casa_grande, alto_presupuesto, atencion_8hs
        //con_patio -> "grande", "chico", "no"
        //atencion_8hs -> "10min", "1h", "8hs"

        //asociacion 2 --> con_patio, otros_animales, casa_grande, duenio_buena_persona, vacunada

        List<CaracteristicaMascota> preg_1 = Arrays.asList(new CaracteristicaMascota(con_patio, "grande"),
                new CaracteristicaMascota(otros_animales, "si"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "10min"));
        List<CaracteristicaMascota> preg_2 = Arrays.asList(new CaracteristicaMascota(con_patio, "no"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(alto_presupuesto, "no"), new CaracteristicaMascota(atencion_8hs, "1h"));
        List<CaracteristicaMascota> preg_3 = Arrays.asList(new CaracteristicaMascota(con_patio, "chico"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "si"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "8hs"));

        List<CaracteristicaMascota> preg_4 = Arrays.asList(new CaracteristicaMascota(con_patio, "si"),
                new CaracteristicaMascota(otros_animales, "si"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(duenio_buena_persona, "si"), new CaracteristicaMascota(vacunada, "no"));
        List<CaracteristicaMascota> preg_5 = Arrays.asList(new CaracteristicaMascota(con_patio, "si"),
                new CaracteristicaMascota(otros_animales, "si"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(duenio_buena_persona, "si"), new CaracteristicaMascota(vacunada, "no"));

        PublicacionAdopcion adopcion_1 = new PublicacionAdopcion(user_1, asociacion_1, perro, preg_1);
        PublicacionAdopcion adopcion_2 = new PublicacionAdopcion(user_2, asociacion_1, gato, preg_2);
        PublicacionAdopcion adopcion_3 = new PublicacionAdopcion(user_3, asociacion_1, minipig, preg_3);
        PublicacionAdopcion adopcion_4 = new PublicacionAdopcion(user_1, asociacion_2, perro2, preg_4);
        PublicacionAdopcion adopcion_5 = new PublicacionAdopcion(user_4, asociacion_2, cerdo, preg_5);
        instancia.agregarPublicacion(adopcion_1);
        instancia.agregarPublicacion(adopcion_2);
        instancia.agregarPublicacion(adopcion_3);
        instancia.agregarPublicacion(adopcion_4);
        instancia.agregarPublicacion(adopcion_5);



        //de intencion de adopcion
        //deben contener las caracteristicas (aqui como preferencias) definidas por su asociacion y ademas las respuestas definidas
        //deben contener las preguntas definidas por su asociacion y ademas las respuestas definidas

        List<CaracteristicaMascota> pref_1_asoc_1 = Arrays.asList(new CaracteristicaMascota(color, "rosa"),
                new CaracteristicaMascota(tamanio, "peque"), new CaracteristicaMascota(pelo, "sin"), new CaracteristicaMascota(cola, "larga"));

        List<CaracteristicaMascota> pref_2_asoc_2 = Arrays.asList(new CaracteristicaMascota(color, "negro"),
                new CaracteristicaMascota(castrada, "si"), new CaracteristicaMascota(cantidadPatas, "cuatro"));

        List<CaracteristicaMascota> pref_2_asoc_1 = Arrays.asList(new CaracteristicaMascota(color, "marron"),
                new CaracteristicaMascota(tamanio, "grande"), new CaracteristicaMascota(pelo, "largo"), new CaracteristicaMascota(cola, "corta"));

        List<CaracteristicaMascota> pregs_1_asoc_1 = Arrays.asList(new CaracteristicaMascota(con_patio, "no"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "si"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "8hs"));
        List<CaracteristicaMascota> pregs_2_asoc_2 = Arrays.asList(new CaracteristicaMascota(con_patio, "grande"),
                new CaracteristicaMascota(otros_animales, "no"), new CaracteristicaMascota(casa_grande, "no"),
                new CaracteristicaMascota(duenio_buena_persona, "no"), new CaracteristicaMascota(vacunada, "si"));
        List<CaracteristicaMascota> pregs_2_asoc_1 = Arrays.asList(new CaracteristicaMascota(con_patio, "chico"),
                new CaracteristicaMascota(otros_animales, "si"), new CaracteristicaMascota(casa_grande, "si"),
                new CaracteristicaMascota(alto_presupuesto, "si"), new CaracteristicaMascota(atencion_8hs, "1h"));

        PublicacionIntencionDeAdopcion intencion_1 = new PublicacionIntencionDeAdopcion(user_4, asociacion_1, TipoMascota.GATO, pref_1_asoc_1, pregs_1_asoc_1);
        PublicacionIntencionDeAdopcion intencion_2 = new PublicacionIntencionDeAdopcion(user_4, asociacion_2, TipoMascota.PERRO, pref_2_asoc_2, pregs_2_asoc_2);
        PublicacionIntencionDeAdopcion intencion_3 = new PublicacionIntencionDeAdopcion(user_5, asociacion_1, TipoMascota.MINIPIG, pref_2_asoc_1, pregs_2_asoc_1);

        instancia.agregarPublicacion(intencion_1);
        instancia.agregarPublicacion(intencion_2);
        instancia.agregarPublicacion(intencion_3);



        //voluntario automatico
        instancia.aceptarPendientes();

    }
}
