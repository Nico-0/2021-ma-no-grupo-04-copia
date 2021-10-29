package com.dds.rescate.server;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.model.Enum.TipoPregunta;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Data {
    public static void init(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager em = entityManagerFactory.createEntityManager();

        PublicacionService instancia = PublicacionService.getInstance();

        NotificadorEmail notiEmail = new NotificadorEmail();
        NotificadorEmail notiEmail2 = new NotificadorEmail();

//Generar usuarios

        Contacto contacto = new Contacto("Lucas", "sin apellido", "🙌@✌.com", notiEmail, 118003330);
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
        //repositorio.agregarUsuario(user_4);
        //repositorio.agregarUsuario(user_5);

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Respuesta").executeUpdate();
        em.createQuery("DELETE FROM Recomendacion").executeUpdate();
        em.createQuery("DELETE FROM PublicacionIntencionDeAdopcion").executeUpdate();
        em.createQuery("DELETE FROM PublicacionAdopcion").executeUpdate();
        em.createQuery("DELETE FROM PublicacionPerdida").executeUpdate();
        em.createQuery("DELETE FROM Mascota").executeUpdate();
        em.createQuery("DELETE FROM UsuarioDuenio").executeUpdate();
        em.createQuery("DELETE FROM DatosPersonales").executeUpdate();
        em.createQuery("DELETE FROM Contacto").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.createQuery("DELETE FROM Comparacion").executeUpdate();
        em.createQuery("DELETE FROM Asociacion").executeUpdate();
        em.createQuery("DELETE FROM RespuestasAsociacion").executeUpdate();
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(contacto);
        em.persist(contacto2);
        em.persist(contacto3);
        em.persist(datos_1);
        em.persist(datos_2);
        em.persist(datos_3);
        em.persist(datos_4);
        em.persist(datos_5);
        em.persist(user_1);
        em.persist(user_2);
        em.persist(user_3);
        em.persist(user_4);
        em.persist(user_5);
        em.getTransaction().commit();



//Generar asociaciones

        //generar caracteristicas
        PreguntaCaracteristica color = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA, "color");
        PreguntaCaracteristica tamanio = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA,"tamanio");
        PreguntaCaracteristica pelo = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA,"pelo");
        PreguntaCaracteristica cola = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA,"cola");
        PreguntaCaracteristica castrada = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA,"castrada");
        PreguntaCaracteristica cantidadPatas = new PreguntaCaracteristica(TipoPregunta.CARACTERISTICA,"cantidadPatas");

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
        RespuestasAsociacion caracteristicaColor = new RespuestasAsociacion(color, Arrays.asList("blanco", "negro", "marron", "rosa"), valoresRespuestaColor);
        RespuestasAsociacion caracteristicaTamanio = new RespuestasAsociacion(tamanio, Arrays.asList("grande", "mediano", "peque"), valoresRespuestaTamanio_1);
        RespuestasAsociacion caracteristicaPelo = new RespuestasAsociacion(pelo, Arrays.asList("largo", "corto", "sin"), valoresRespuestaPelo_1);
        RespuestasAsociacion caracteristicaCola = new RespuestasAsociacion(cola, Arrays.asList("larga", "corta"), valoresRespuestaCola_1);
        List<RespuestasAsociacion> caracteristicas_1 = Arrays.asList(caracteristicaColor, caracteristicaTamanio, caracteristicaPelo, caracteristicaCola);


        //para asociacion 2
        Comparacion indistinto_si_1 = new Comparacion("indistinto","si",10);
        Comparacion indistinto_no_1 = new Comparacion("indistinto","no",10);
        List<Comparacion> valoresRespuestaCastrada_1 = Arrays.asList(indistinto_si_1, indistinto_no_1);

        Comparacion dos_tres_1 = new Comparacion("dos","tres",5);
        Comparacion cuatro_tres_1 = new Comparacion("cuatro","tres",5);
        List<Comparacion> valoresRespuestaPatas_1 = Arrays.asList(dos_tres_1, cuatro_tres_1);

        RespuestasAsociacion caracteristicaCastrada = new RespuestasAsociacion(castrada, Arrays.asList("si", "no", "indistinto"), valoresRespuestaCastrada_1);
        RespuestasAsociacion caracteristicaPatas = new RespuestasAsociacion(cantidadPatas, Arrays.asList("dos", "tres", "cuatro"), valoresRespuestaPatas_1);
        List<RespuestasAsociacion> caracteristicas_2 = Arrays.asList(caracteristicaColor, caracteristicaCastrada, caracteristicaPatas);


        //generar preguntas generales
        PreguntaCaracteristica con_patio = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Con patio", "⸮Cuenta con un patio enorme?");
        PreguntaCaracteristica otros_animales = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Otros animales", "cuenta o no con otros animales");
        PreguntaCaracteristica casa_grande = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Casa grande");

        Comparacion patio_grande_chico_1 = new Comparacion("grande","chico",5);
        Comparacion patio_chico_no_1 = new Comparacion("chico","no",5);
        List<Comparacion> valoresRespuestaPatio_1 = Arrays.asList(patio_grande_chico_1, patio_chico_no_1);

        List<Comparacion> valoresRespuestaOtrosAnimales_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaCasaGrande_1 = Collections.emptyList();

        RespuestasAsociacion preguntaPatio = new RespuestasAsociacion(con_patio, Arrays.asList("grande", "chico", "no"), valoresRespuestaPatio_1);
        RespuestasAsociacion preguntaOtrosAnimales = new RespuestasAsociacion(otros_animales, Arrays.asList("si", "no"), valoresRespuestaOtrosAnimales_1);
        RespuestasAsociacion preguntaCasaGrande = new RespuestasAsociacion(casa_grande, Arrays.asList("si", "no"), valoresRespuestaCasaGrande_1);


        //generar preguntas
        PreguntaCaracteristica alto_presupuesto = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Alto presupuesto");
        PreguntaCaracteristica atencion_8hs = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Atencion 8hs");
        PreguntaCaracteristica duenio_buena_persona = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Duenio buena persona", "Usted dueño fue buena persona?");
        PreguntaCaracteristica vacunada = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"Vacunada");

        Comparacion atencion_10min_1h_1 = new Comparacion("10min","1h",5);
        Comparacion atencion_1h_8hs_1 = new Comparacion("1h","8hs",5);
        List<Comparacion> valoresRespuestaAtencion_1 = Arrays.asList(atencion_10min_1h_1, atencion_1h_8hs_1);

        List<Comparacion> valoresRespuestaAltoPresupuesto_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaBuenaPersona_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaVacunada_1 = Collections.emptyList();

        RespuestasAsociacion preguntaAltoPresupuesto = new RespuestasAsociacion(alto_presupuesto, Arrays.asList("si", "no"), valoresRespuestaAltoPresupuesto_1);
        RespuestasAsociacion preguntaAtencion = new RespuestasAsociacion(atencion_8hs, Arrays.asList("10min", "1h", "8hs"), valoresRespuestaAtencion_1);
        RespuestasAsociacion preguntaBuenaPersona = new RespuestasAsociacion(duenio_buena_persona, Arrays.asList("si", "no"), valoresRespuestaBuenaPersona_1);
        RespuestasAsociacion preguntaVacunada = new RespuestasAsociacion(vacunada, Arrays.asList("si", "no"), valoresRespuestaVacunada_1);


        List<RespuestasAsociacion> preguntas_1 = Arrays.asList(preguntaPatio, preguntaOtrosAnimales, preguntaCasaGrande, preguntaAltoPresupuesto, preguntaAtencion);
        List<RespuestasAsociacion> preguntas_2 = Arrays.asList(preguntaPatio, preguntaOtrosAnimales, preguntaCasaGrande, preguntaBuenaPersona, preguntaVacunada);

        //y por ultimo estas dos lineas
        Asociacion asociacion_1 = new Asociacion("patitas al rescate",caracteristicas_1,preguntas_1);
        Asociacion asociacion_2 = new Asociacion("orejas rescatadas",caracteristicas_2,preguntas_2);

        em.getTransaction().begin();

        em.persist(color);
        em.persist(tamanio);
        em.persist(pelo);
        em.persist(cola);
        em.persist(castrada);
        em.persist(cantidadPatas);

        em.persist(con_patio);
        em.persist(otros_animales);
        em.persist(casa_grande);

        em.persist(alto_presupuesto);
        em.persist(atencion_8hs);
        em.persist(duenio_buena_persona);
        em.persist(vacunada);

        em.persist(blanco_rosa_1);
        em.persist(negro_marron_1);
        em.persist(grande_mediano_1);
        em.persist(mediano_peque_1);
        em.persist(largo_corto_1);
        em.persist(corto_sin_1);

        em.persist(caracteristicaColor);
        em.persist(caracteristicaTamanio);
        em.persist(caracteristicaPelo);
        em.persist(caracteristicaCola);
        em.persist(caracteristicaCastrada);
        em.persist(caracteristicaPatas);

        em.persist(indistinto_no_1);
        em.persist(indistinto_si_1);
        em.persist(dos_tres_1);
        em.persist(cuatro_tres_1);

        em.persist(patio_grande_chico_1);
        em.persist(patio_chico_no_1);
        em.persist(preguntaPatio);
        em.persist(preguntaOtrosAnimales);
        em.persist(preguntaCasaGrande);

        em.persist(atencion_1h_8hs_1);
        em.persist(atencion_10min_1h_1);
        em.persist(preguntaAltoPresupuesto);
        em.persist(preguntaAtencion);
        em.persist(preguntaBuenaPersona);
        em.persist(preguntaVacunada);

        em.persist(asociacion_1);
        em.persist(asociacion_2);
        em.getTransaction().commit();


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

        List<Respuesta> carac_perro = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(tamanio, "grande"), new Respuesta(pelo, "largo"), new Respuesta(cola, "corta"));

        List<Respuesta> carac_perro_chico = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(tamanio, "peque"), new Respuesta(pelo, "corto"), new Respuesta(cola, "larga"));

        List<Respuesta> carac_gato = Arrays.asList(new Respuesta(color, "blanco"),
                new Respuesta(castrada, "si"), new Respuesta(cantidadPatas, "cuatro"));

        List<Respuesta> carac_pig = Arrays.asList(new Respuesta(color, "rosa"),
                new Respuesta(tamanio, "mediano"), new Respuesta(pelo, "sin"), new Respuesta(cola, "corta"));

        List<Respuesta> carac_cerdo = Arrays.asList(new Respuesta(color, "blanco"),
                new Respuesta(castrada, "indistinto"), new Respuesta(cantidadPatas, "tres"));

        Mascota perro = new Mascota(TipoMascota.PERRO, "Guay", "guauguau", "el mejor amigo", asociacion_1, carac_perro, Sexo.MACHO,"perro.jpg");
        Mascota gato = new Mascota(TipoMascota.GATO, "Michi", "miaumiau", "llevenselo no para de subirse a la mesa", asociacion_2, carac_gato, Sexo.HEMBRA, "gato.jpg");
        Mascota minipig = new Mascota(TipoMascota.MINIPIG, "CERDO", "oinkoink", "ocupa una cama de dos plazas", asociacion_1, carac_pig, Sexo.HEMBRA, "minipig.jpg");
        Mascota perro2 = new Mascota(TipoMascota.PERRO, "Pedro mini", "Caniche", "demasiado chico", asociacion_1, carac_perro_chico, Sexo.MACHO,"perroCaniche.jpg");
        Mascota cerdo = new Mascota(TipoMascota.MINIPIG, "George", "dinosaurio", "salta en charcos de lodo", asociacion_2, carac_cerdo, Sexo.MACHO, "minipig.jpg");

        em.getTransaction().begin();
        em.persist(perro);
        em.persist(gato);
        em.persist(minipig);
        em.persist(perro2);
        em.persist(cerdo);

        carac_perro.forEach(em::persist);
        carac_perro_chico.forEach(em::persist);
        carac_gato.forEach(em::persist);
        carac_pig.forEach(em::persist);
        carac_cerdo.forEach(em::persist);

        //Agrego mascota a un Usuario(el que esta en el repo)
        user_4.agregarMascota(perro);
        user_5.agregarMascota(gato);
        user_4.agregarMascota(minipig);
        user_4.agregarMascota(perro2);
        user_4.agregarMascota(cerdo);
        em.getTransaction().commit();


//Generar publicaciones

        //de mascota perdida
        PublicacionPerdida perdida_1 = new PublicacionPerdida(user_1, asociacion_1, TipoMascota.GATO, "gato.jpg", EstadoEncontrada.EXCELENTE,"",contacto);
        PublicacionPerdida perdida_2 = new PublicacionPerdida(user_2, asociacion_2, TipoMascota.MINIPIG,"minipig.jpg", EstadoEncontrada.BIEN,"",contacto);
        PublicacionPerdida perdida_3 = new PublicacionPerdida(user_3, asociacion_1, TipoMascota.PERRO,"perro.jpg", EstadoEncontrada.MASO,"",contacto);
        //instancia.agregarPublicacion(perdida_1);
       // instancia.agregarPublicacion(perdida_2);
       // instancia.agregarPublicacion(perdida_3);


        //de adopcion
        //no esta puesta la restriccion de publicar en la misma asociacion que la mascota este registrada
        //deben contener las preguntas definidas por su asociacion y ademas las respuestas definidas
        //asociacion 1 --> con_patio, otros_animales, casa_grande, alto_presupuesto, atencion_8hs
        //con_patio -> "grande", "chico", "no"
        //atencion_8hs -> "10min", "1h", "8hs"

        //asociacion 2 --> con_patio, otros_animales, casa_grande, duenio_buena_persona, vacunada

        List<Respuesta> preg_1 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "10min"));
        List<Respuesta> preg_2 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "no"),
                new Respuesta(alto_presupuesto, "no"), new Respuesta(atencion_8hs, "1h"));
        List<Respuesta> preg_3 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));

        List<Respuesta> preg_4 = Arrays.asList(new Respuesta(con_patio, "si"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "no"));
        List<Respuesta> preg_5 = Arrays.asList(new Respuesta(con_patio, "si"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "no"));

        PublicacionAdopcion adopcion_1 = new PublicacionAdopcion(user_1, asociacion_1, perro, preg_1);
        PublicacionAdopcion adopcion_2 = new PublicacionAdopcion(user_2, asociacion_1, gato, preg_2);
        PublicacionAdopcion adopcion_3 = new PublicacionAdopcion(user_3, asociacion_1, minipig, preg_3);
        PublicacionAdopcion adopcion_4 = new PublicacionAdopcion(user_1, asociacion_2, perro2, preg_4);
        PublicacionAdopcion adopcion_5 = new PublicacionAdopcion(user_4, asociacion_2, cerdo, preg_5);
        /*instancia.agregarPublicacion(adopcion_1);
        instancia.agregarPublicacion(adopcion_2);
        instancia.agregarPublicacion(adopcion_3);
        instancia.agregarPublicacion(adopcion_4);
        instancia.agregarPublicacion(adopcion_5);*/



        //de intencion de adopcion
        //deben contener las caracteristicas (aqui como preferencias) definidas por su asociacion y ademas las respuestas definidas
        //deben contener las preguntas definidas por su asociacion y ademas las respuestas definidas

        List<Respuesta> pref_1_asoc_1 = Arrays.asList(new Respuesta(color, "rosa"),
                new Respuesta(tamanio, "peque"), new Respuesta(pelo, "sin"), new Respuesta(cola, "larga"));

        List<Respuesta> pref_2_asoc_2 = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(castrada, "si"), new Respuesta(cantidadPatas, "cuatro"));

        List<Respuesta> pref_2_asoc_1 = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(tamanio, "grande"), new Respuesta(pelo, "largo"), new Respuesta(cola, "corta"));

        List<Respuesta> pregs_1_asoc_1 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        List<Respuesta> pregs_2_asoc_2 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "no"), new Respuesta(vacunada, "si"));
        List<Respuesta> pregs_2_asoc_1 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "1h"));

        PublicacionIntencionDeAdopcion intencion_1 = new PublicacionIntencionDeAdopcion(user_4, asociacion_1, TipoMascota.GATO, pref_1_asoc_1, pregs_1_asoc_1);
        PublicacionIntencionDeAdopcion intencion_2 = new PublicacionIntencionDeAdopcion(user_4, asociacion_2, TipoMascota.PERRO, pref_2_asoc_2, pregs_2_asoc_2);
        PublicacionIntencionDeAdopcion intencion_3 = new PublicacionIntencionDeAdopcion(user_5, asociacion_1, TipoMascota.MINIPIG, pref_2_asoc_1, pregs_2_asoc_1);

        /*instancia.agregarPublicacion(intencion_1);
        instancia.agregarPublicacion(intencion_2);
        instancia.agregarPublicacion(intencion_3);*/

        em.getTransaction().begin();

        em.persist(perdida_1);
        em.persist(perdida_2);
        em.persist(perdida_3);

        preg_1.forEach(em::persist);
        preg_2.forEach(em::persist);
        preg_3.forEach(em::persist);
        preg_4.forEach(em::persist);
        preg_5.forEach(em::persist);
        em.persist(adopcion_1);
        em.persist(adopcion_2);
        em.persist(adopcion_3);
        em.persist(adopcion_4);
        em.persist(adopcion_5);

        pref_1_asoc_1.forEach(em::persist);
        pref_2_asoc_2.forEach(em::persist);
        pref_2_asoc_1.forEach(em::persist);
        pregs_1_asoc_1.forEach(em::persist);
        pregs_2_asoc_2.forEach(em::persist);
        pregs_2_asoc_1.forEach(em::persist);
        em.persist(intencion_1);
        em.persist(intencion_2);
        em.persist(intencion_3);

        em.getTransaction().commit();


        em.getTransaction().begin();
        //voluntario automatico
        PublicacionService repo = new PublicacionService(em);
        repo.aceptarPendientes();

        em.getTransaction().commit();
    }
}
