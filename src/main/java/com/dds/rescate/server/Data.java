package com.dds.rescate.server;

import com.dds.rescate.model.*;
import com.dds.rescate.model.Enum.EstadoEncontrada;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import com.dds.rescate.model.Enum.TipoPregunta;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.PublicacionService;
import com.dds.rescate.service.RepoAsociacion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Data {

    public void limpiar(EntityManager em){

        em.createQuery("DELETE FROM Respuesta").executeUpdate();
        em.createQuery("DELETE FROM Recomendacion").executeUpdate();
        em.createQuery("DELETE FROM PublicacionIntencionDeAdopcion").executeUpdate();
        em.createQuery("DELETE FROM PublicacionAdopcion").executeUpdate();
        em.createQuery("DELETE FROM PublicacionPerdida").executeUpdate();
        em.createQuery("DELETE FROM Mascota").executeUpdate();
        em.createQuery("DELETE FROM UsuarioDuenio").executeUpdate();
        em.createQuery("DELETE FROM UsuarioAdministrador").executeUpdate();
        em.createQuery("DELETE FROM UsuarioVoluntario").executeUpdate();
        em.createQuery("DELETE FROM DatosPersonales").executeUpdate();
        em.createQuery("DELETE FROM Contacto").executeUpdate();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.createQuery("DELETE FROM Comparacion").executeUpdate();
        em.createQuery("DELETE FROM Asociacion").executeUpdate();
        em.createQuery("DELETE FROM RespuestasAsociacion").executeUpdate();
        em.createQuery("DELETE FROM PreguntaCaracteristica").executeUpdate();

       /* RepoAsociacion repoAsociacion = new RepoAsociacion(em);
        Asociacion asociacion = repoAsociacion.getAsociacionByNombre("patitas al rescate");

        UsuarioAdministrador admin_1 = new UsuarioAdministrador("adminPatitas", "adminPatitas", asociacion);
        em.persist(admin_1);*/

    }

    public void init(EntityManager em){


        NotificadorEmail notiEmail = new NotificadorEmail();

//Generar usuarios

        Contacto contacto = new Contacto("Papo", "Pepe", "🙌@✌.com", notiEmail, 118003330);
        Contacto contacto2 = new Contacto("Pabloman", "so", "cualquiera2@gmail.com", notiEmail , 1165565654);
        Contacto contacto3 = new Contacto("Cristi", "Naa", "cualquiera3@gmail.com", notiEmail , 1165565654);
        Contacto contacto4 = new Contacto("Lunes", "Primero", "cualquiera4@gmail.com", notiEmail , 1185855858);
        Contacto contacto5 = new Contacto("Jueves", "Segundo", "cualquiera5@gmail.com", notiEmail , 1165565654);

        DatosPersonales datos_1 = new DatosPersonales("pepe", null,null,null,contacto);
        DatosPersonales datos_2 = new DatosPersonales("pablo", null,null,null,contacto2);
        DatosPersonales datos_3 = new DatosPersonales("cristina", null ,null,null,contacto3);
        DatosPersonales datos_4 = new DatosPersonales("Lunes", "Testing" , new Date( 96, 9, 19),12345678,contacto4);
        DatosPersonales datos_5 = new DatosPersonales("Jueves", "Testing" , new Date( 05, 2, 15),87654321,contacto5);

        UsuarioDuenio user_1 = new UsuarioDuenio("pepedestroyer9000","@$&*#HFGG445d450",datos_1);
        UsuarioDuenio user_2 = new UsuarioDuenio("pablito15","1@3$5^7*9)h",datos_2);
        UsuarioDuenio user_3 = new UsuarioDuenio("lacris","753ASADo",datos_3);
        //Usuarios en repo
        UsuarioDuenio user_4 = new UsuarioDuenio("Usuario1","Usuario1",datos_4);
        UsuarioDuenio user_5 = new UsuarioDuenio("JuevesTrece","Contrasenia",datos_5);
        //Test usuario guardado

        //repositorio.agregarUsuario(user_4);
        //repositorio.agregarUsuario(user_5);



        em.persist(contacto);
        em.persist(contacto2);
        em.persist(contacto3);
        em.persist(contacto4);
        em.persist(contacto5);
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
        PreguntaCaracteristica con_patio = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"con_patio", "⸮Cuenta con un patio enorme?");
        PreguntaCaracteristica otros_animales = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"otros_animales", "cuenta o no con otros animales");
        PreguntaCaracteristica casa_grande = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"casa_grande");

        Comparacion patio_grande_chico_1 = new Comparacion("grande","chico",5);
        Comparacion patio_chico_no_1 = new Comparacion("chico","no",5);
        List<Comparacion> valoresRespuestaPatio_1 = Arrays.asList(patio_grande_chico_1, patio_chico_no_1);

        List<Comparacion> valoresRespuestaOtrosAnimales_1 = Collections.emptyList();
        List<Comparacion> valoresRespuestaCasaGrande_1 = Collections.emptyList();

        RespuestasAsociacion preguntaPatio = new RespuestasAsociacion(con_patio, Arrays.asList("grande", "chico", "no"), valoresRespuestaPatio_1);
        RespuestasAsociacion preguntaOtrosAnimales = new RespuestasAsociacion(otros_animales, Arrays.asList("si", "no"), valoresRespuestaOtrosAnimales_1);
        RespuestasAsociacion preguntaCasaGrande = new RespuestasAsociacion(casa_grande, Arrays.asList("si", "no"), valoresRespuestaCasaGrande_1);


        //generar preguntas
        PreguntaCaracteristica alto_presupuesto = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"alto_presupuesto");
        PreguntaCaracteristica atencion_8hs = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"atencion_8hs");
        PreguntaCaracteristica duenio_buena_persona = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"duenio_buena_persona", "Usted dueño fue buena persona?");
        PreguntaCaracteristica vacunada = new PreguntaCaracteristica(TipoPregunta.PREGUNTA,"vacunada");

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


        //administradores y voluntarios

        UsuarioAdministrador admin_1 = new UsuarioAdministrador("adminPatitas", "adminPatitas", asociacion_1);
        UsuarioAdministrador admin_2 = new UsuarioAdministrador("adminOrejas", "adminOrejas", asociacion_2);
        em.persist(admin_1);
        em.persist(admin_2);
        UsuarioVoluntario vol_1 = new UsuarioVoluntario("volPatitas", "volPatitas", asociacion_1);
        UsuarioVoluntario vol_2 = new UsuarioVoluntario("volOrejas", "volOrejas", asociacion_2);
        em.persist(vol_1);
        em.persist(vol_2);


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
        user_1.agregarMascota(perro);
        user_2.agregarMascota(gato);
        user_3.agregarMascota(minipig);
        user_4.agregarMascota(perro2);
        user_5.agregarMascota(cerdo);



//Generar publicaciones

        Mascota mascota_p1 = new Mascota(TipoMascota.GATO, "Name5", "Como se llama??", "Es gato", asociacion_1, carac_perro, Sexo.MACHO, "gato.jpg");
        Mascota mascota_p2 = new Mascota(TipoMascota.MINIPIG, "Name6", "Decime un nombre", "Es cerdo", asociacion_2, carac_gato, Sexo.MACHO, "minipig.jpg");
        Mascota mascota_p3 = new Mascota(TipoMascota.PERRO, "Name7", "El que adopta elige", "Es perra", asociacion_1, carac_pig, Sexo.HEMBRA, "perro.jpg");
        em.persist(mascota_p1);
        em.persist(mascota_p2);
        em.persist(mascota_p3);

        //de mascota perdida
        PublicacionPerdida perdida_1 = new PublicacionPerdida(user_1, asociacion_1, mascota_p1, EstadoEncontrada.EXCELENTE,"",contacto);
        PublicacionPerdida perdida_2 = new PublicacionPerdida(user_2, asociacion_2, mascota_p2, EstadoEncontrada.BIEN,"",contacto2);
        PublicacionPerdida perdida_3 = new PublicacionPerdida(user_3, asociacion_1, mascota_p3, EstadoEncontrada.MASO,"",contacto3);
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
        List<Respuesta> preg_4 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));

        List<Respuesta> preg_3 = Arrays.asList(new Respuesta(con_patio, "si"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "no"));
        List<Respuesta> preg_5 = Arrays.asList(new Respuesta(con_patio, "si"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "no"));

        PublicacionAdopcion adopcion_1 = new PublicacionAdopcion(user_1, asociacion_1, perro, preg_1);
        PublicacionAdopcion adopcion_2 = new PublicacionAdopcion(user_2, asociacion_1, gato, preg_2);
        PublicacionAdopcion adopcion_3 = new PublicacionAdopcion(user_3, asociacion_2, minipig, preg_3);
        PublicacionAdopcion adopcion_4 = new PublicacionAdopcion(user_4, asociacion_1, perro2, preg_4);
        PublicacionAdopcion adopcion_5 = new PublicacionAdopcion(user_5, asociacion_2, cerdo, preg_5);
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





        //voluntario automatico
        PublicacionService repo = new PublicacionService(em);
        repo.aceptarPendientes();


    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public PreguntaCaracteristica obtenerPregunta(String nombre, EntityManager em){
        return em.createQuery("from PreguntaCaracteristica p where p.nombre = ?1", PreguntaCaracteristica.class)
                .setParameter(1, nombre)
                .getSingleResult();
    }

    public Asociacion obtenerAsociacion(String nombre, EntityManager em){
        return em.createQuery("from Asociacion a where a.nombre = ?1", Asociacion.class)
                .setParameter(1, nombre)
                .getSingleResult();
    }

    public UsuarioDuenio obtenerUsuario(String username, EntityManager em){
        return em.createQuery("from UsuarioDuenio u where u.username = ?1", UsuarioDuenio.class)
                .setParameter(1, username)
                .getSingleResult();
    }


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void init_extra(EntityManager em){

        //caracteristicas
        PreguntaCaracteristica color = obtenerPregunta("color", em);
        PreguntaCaracteristica tamanio = obtenerPregunta("tamanio", em);
        PreguntaCaracteristica pelo = obtenerPregunta("pelo", em);
        PreguntaCaracteristica cola = obtenerPregunta("cola", em);
        PreguntaCaracteristica castrada = obtenerPregunta("castrada", em);
        PreguntaCaracteristica cantidadPatas = obtenerPregunta("cantidadPatas", em);

        //preguntas
        PreguntaCaracteristica con_patio = obtenerPregunta("con_patio", em);
        PreguntaCaracteristica otros_animales = obtenerPregunta("otros_animales", em);
        PreguntaCaracteristica casa_grande = obtenerPregunta("casa_grande", em);
        PreguntaCaracteristica alto_presupuesto = obtenerPregunta("alto_presupuesto", em);
        PreguntaCaracteristica atencion_8hs = obtenerPregunta("atencion_8hs", em);
        PreguntaCaracteristica duenio_buena_persona = obtenerPregunta("duenio_buena_persona", em);
        PreguntaCaracteristica vacunada = obtenerPregunta("vacunada", em);

        Asociacion asociacion_1 =  obtenerAsociacion("patitas al rescate", em);
        Asociacion asociacion_2 =  obtenerAsociacion("orejas rescatadas", em);

        UsuarioDuenio user_4 = obtenerUsuario("Usuario1", em);


        NotificadorEmail notiEmail = new NotificadorEmail();

        Contacto contacto4 = user_4.getPerfil().getContactos().get(0);
        Contacto contacto5 = new Contacto("Peter", "Sobrino", "email@utn.com", notiEmail, 1199998888);
        em.persist(contacto5);

        DatosPersonales datos_5 = new DatosPersonales("Peter", "Sobrino" , new Date( 06, 4, 11),87631321,contacto5);
        em.persist(datos_5);
        UsuarioDuenio user_5 = new UsuarioDuenio("Usuario2","Usuario2",datos_5);
        em.persist(user_5);

//Mascotas
        List<Respuesta> carac_perro = Arrays.asList(new Respuesta(color, "blanco"),
                new Respuesta(tamanio, "grande"), new Respuesta(pelo, "largo"), new Respuesta(cola, "larga"));
        carac_perro.forEach(em::persist);
        List<Respuesta> carac_perro_grande = Arrays.asList(new Respuesta(color, "blanco"),
                new Respuesta(tamanio, "mediano"), new Respuesta(pelo, "sin"), new Respuesta(cola, "corta"));
        carac_perro_grande.forEach(em::persist);
        List<Respuesta> carac_cat = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(tamanio, "peque"), new Respuesta(pelo, "corto"), new Respuesta(cola, "corta"));
        carac_cat.forEach(em::persist);
        List<Respuesta> carac_gato = Arrays.asList(new Respuesta(color, "rosa"),
                new Respuesta(castrada, "si"), new Respuesta(cantidadPatas, "dos"));
        carac_gato.forEach(em::persist);
        List<Respuesta> carac_cerdo = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(castrada, "no"), new Respuesta(cantidadPatas, "tres"));
        carac_cerdo.forEach(em::persist);


        List<Respuesta> carac_perro2 = Arrays.asList(new Respuesta(color, "rosa"),
                new Respuesta(tamanio, "peque"), new Respuesta(pelo, "largo"), new Respuesta(cola, "larga"));
        carac_perro2.forEach(em::persist);
        List<Respuesta> carac_perro_chico = Arrays.asList(new Respuesta(color, "rosa"),
                new Respuesta(tamanio, "peque"), new Respuesta(pelo, "sin"), new Respuesta(cola, "larga"));
        carac_perro_chico.forEach(em::persist);
        List<Respuesta> carac_pig2 = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(tamanio, "mediano"), new Respuesta(pelo, "sin"), new Respuesta(cola, "larga"));
        carac_pig2.forEach(em::persist);
        List<Respuesta> carac_gato2 = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(castrada, "indistinto"), new Respuesta(cantidadPatas, "cuatro"));
        carac_gato2.forEach(em::persist);
        List<Respuesta> carac_cerdo2 = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(castrada, "si"), new Respuesta(cantidadPatas, "cuatro"));
        carac_cerdo2.forEach(em::persist);

        Mascota perro = new Mascota(TipoMascota.PERRO, "Beethoven", "Brownie", "pianista", asociacion_1, carac_perro, Sexo.HEMBRA,"perro.jpg");
        Mascota perro2 = new Mascota(TipoMascota.PERRO, "Pongo", "Martini", "un caniche sin mas", asociacion_1, carac_perro_grande, Sexo.MACHO,"perroCaniche.jpg");
        Mascota cat = new Mascota(TipoMascota.GATO, "Laika", "Cookie", "NO SE", asociacion_1, carac_cat, Sexo.HEMBRA, "gato.jpg");
        Mascota gato = new Mascota(TipoMascota.GATO, "Hachiko", "Comino", "nose", asociacion_2, carac_gato, Sexo.HEMBRA, "gato.jpg");
        Mascota cerdo = new Mascota(TipoMascota.MINIPIG, "Dama", "Golfo", "tiene mirada fea", asociacion_2, carac_cerdo, Sexo.HEMBRA, "minipig.jpg");

        Mascota perro4 = new Mascota(TipoMascota.PERRO, "Scooby", "Doo", "scubi galleta?", asociacion_1, carac_perro2, Sexo.MACHO,"perro.jpg");
        Mascota perro5 = new Mascota(TipoMascota.PERRO, "Odie", "Gazpacho", "hora de comer", asociacion_1, carac_perro_chico, Sexo.HEMBRA,"perroCaniche.jpg");
        Mascota minipig4 = new Mascota(TipoMascota.MINIPIG, "Goofy", "Miel", "chau", asociacion_1, carac_pig2, Sexo.HEMBRA, "minipig.jpg");
        Mascota gato4 = new Mascota(TipoMascota.GATO, "Rex", "Pizza", "hola", asociacion_2, carac_gato2, Sexo.MACHO, "gato.jpg");
        Mascota cerdo4 = new Mascota(TipoMascota.MINIPIG, "Ayudante", "Chocolate", "no tengo dinero", asociacion_2, carac_cerdo2, Sexo.MACHO, "minipig.jpg");

        em.persist(perro);
        em.persist(perro2);
        em.persist(cat);
        em.persist(gato);
        em.persist(cerdo);

        em.persist(perro4);
        em.persist(perro5);
        em.persist(minipig4);
        em.persist(gato4);
        em.persist(cerdo4);

        user_4.agregarMascota(perro);
        user_4.agregarMascota(perro2);
        user_4.agregarMascota(cat);
        user_4.agregarMascota(gato);
        user_4.agregarMascota(cerdo);

        user_5.agregarMascota(perro4);
        user_5.agregarMascota(perro5);
        user_5.agregarMascota(minipig4);
        user_5.agregarMascota(gato4);
        user_5.agregarMascota(cerdo4);


//publis perdidas

        Mascota mascota_p1 = new Mascota(TipoMascota.GATO, "No", "Tiene", "", asociacion_1, carac_perro, Sexo.HEMBRA, "gato.jpg");
        Mascota mascota_p2 = new Mascota(TipoMascota.MINIPIG, "Le", "Encantaria", "", asociacion_1, carac_cat, Sexo.MACHO, "minipig.jpg");
        Mascota mascota_p3 = new Mascota(TipoMascota.PERRO, "Tener", "Nombre", "", asociacion_2, carac_gato2, Sexo.HEMBRA, "perro.jpg");
        Mascota mascota_p4 = new Mascota(TipoMascota.GATO, "Pero", "no", "", asociacion_2, carac_cerdo2, Sexo.MACHO, "gato.jpg");
        Mascota mascota_p5 = new Mascota(TipoMascota.MINIPIG, "La", "Voy", "", asociacion_2, carac_gato, Sexo.MACHO, "minipig.jpg");
        Mascota mascota_p6 = new Mascota(TipoMascota.PERRO, "Adoptar", "Yo", "", asociacion_1, carac_pig2, Sexo.HEMBRA, "perro.jpg");
        em.persist(mascota_p1);
        em.persist(mascota_p2);
        em.persist(mascota_p3);
        em.persist(mascota_p4);
        em.persist(mascota_p5);
        em.persist(mascota_p6);


        PublicacionPerdida perdida_1 = new PublicacionPerdida(user_4, asociacion_1, mascota_p1, EstadoEncontrada.EXCELENTE,"",contacto4);
        PublicacionPerdida perdida_2 = new PublicacionPerdida(user_5, asociacion_1, mascota_p2, EstadoEncontrada.MASO,"",contacto5);
        PublicacionPerdida perdida_3 = new PublicacionPerdida(user_4, asociacion_2, mascota_p3, EstadoEncontrada.BIEN,"",contacto4);
        PublicacionPerdida perdida_4 = new PublicacionPerdida(user_5, asociacion_2, mascota_p4, EstadoEncontrada.MASO,"",contacto5);
        PublicacionPerdida perdida_5 = new PublicacionPerdida(user_4, asociacion_2, mascota_p5, EstadoEncontrada.BIEN,"",contacto4);
        PublicacionPerdida perdida_6 = new PublicacionPerdida(user_5, asociacion_1, mascota_p6, EstadoEncontrada.EXCELENTE,"",contacto5);
        em.persist(perdida_1);
        em.persist(perdida_2);
        em.persist(perdida_3);
        em.persist(perdida_4);
        em.persist(perdida_5);
        em.persist(perdida_6);


//publis adopcion


        List<Respuesta> preg_1 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "no"), new Respuesta(vacunada, "no"));
        preg_1.forEach(em::persist);
        List<Respuesta> preg_2 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "no"),
                new Respuesta(alto_presupuesto, "no"), new Respuesta(atencion_8hs, "1h"));
        preg_1.forEach(em::persist);
        List<Respuesta> preg_3 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(duenio_buena_persona, "no"), new Respuesta(vacunada, "no"));
        preg_1.forEach(em::persist);
        List<Respuesta> preg_4 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "si"));
        preg_1.forEach(em::persist);
        List<Respuesta> preg_5 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(alto_presupuesto, "no"), new Respuesta(atencion_8hs, "10min"));
        preg_1.forEach(em::persist);

        List<Respuesta> preg_6 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        preg_6.forEach(em::persist);
        List<Respuesta> preg_7 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        preg_7.forEach(em::persist);
        List<Respuesta> preg_8 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "si"));
        preg_8.forEach(em::persist);
        List<Respuesta> preg_9 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "no"), new Respuesta(vacunada, "si"));
        preg_9.forEach(em::persist);
        List<Respuesta> preg_10 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        preg_10.forEach(em::persist);

        PublicacionAdopcion adopcion_1 = new PublicacionAdopcion(user_4, asociacion_2, perro, preg_1);
        PublicacionAdopcion adopcion_2 = new PublicacionAdopcion(user_4, asociacion_1, perro2, preg_2);
        PublicacionAdopcion adopcion_3 = new PublicacionAdopcion(user_4, asociacion_2, cat, preg_3);
        PublicacionAdopcion adopcion_4 = new PublicacionAdopcion(user_4, asociacion_2, gato, preg_4);
        PublicacionAdopcion adopcion_5 = new PublicacionAdopcion(user_4, asociacion_1, cerdo, preg_5);

        PublicacionAdopcion adopcion_6 = new PublicacionAdopcion(user_5, asociacion_1, perro4, preg_6);
        PublicacionAdopcion adopcion_7 = new PublicacionAdopcion(user_5, asociacion_1, perro5, preg_7);
        PublicacionAdopcion adopcion_8 = new PublicacionAdopcion(user_5, asociacion_2, minipig4, preg_8);
        PublicacionAdopcion adopcion_9 = new PublicacionAdopcion(user_5, asociacion_2, gato4, preg_9);
        PublicacionAdopcion adopcion_10 = new PublicacionAdopcion(user_5, asociacion_1, cerdo4, preg_10);

        em.persist(adopcion_1);
        em.persist(adopcion_2);
        em.persist(adopcion_3);
        em.persist(adopcion_4);
        em.persist(adopcion_5);
        em.persist(adopcion_6);
        em.persist(adopcion_7);
        em.persist(adopcion_8);
        em.persist(adopcion_9);
        em.persist(adopcion_10);

//publis intencion


        List<Respuesta> pref_1_asoc_1 = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(tamanio, "grande"), new Respuesta(pelo, "sin"), new Respuesta(cola, "corta"));
        pref_1_asoc_1.forEach(em::persist);
        List<Respuesta> pref_2_asoc_2 = Arrays.asList(new Respuesta(color, "blanco"),
                new Respuesta(castrada, "no"), new Respuesta(cantidadPatas, "cuatro"));
        pref_2_asoc_2.forEach(em::persist);
        List<Respuesta> pref_3_asoc_2 = Arrays.asList(new Respuesta(color, "negro"),
                new Respuesta(castrada, "si"), new Respuesta(cantidadPatas, "dos"));
        pref_3_asoc_2.forEach(em::persist);

        List<Respuesta> pregs_1_asoc_1 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        pregs_1_asoc_1.forEach(em::persist);
        List<Respuesta> pregs_2_asoc_2 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(duenio_buena_persona, "no"), new Respuesta(vacunada, "si"));
        pregs_2_asoc_2.forEach(em::persist);
        List<Respuesta> pregs_3_asoc_2 = Arrays.asList(new Respuesta(con_patio, "grande"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "no"), new Respuesta(vacunada, "no"));
        pregs_3_asoc_2.forEach(em::persist);

        PublicacionIntencionDeAdopcion intencion_1 = new PublicacionIntencionDeAdopcion(user_4, asociacion_1, TipoMascota.GATO, pref_1_asoc_1, pregs_1_asoc_1);
        PublicacionIntencionDeAdopcion intencion_2 = new PublicacionIntencionDeAdopcion(user_4, asociacion_2, TipoMascota.PERRO, pref_2_asoc_2, pregs_2_asoc_2);
        PublicacionIntencionDeAdopcion intencion_3 = new PublicacionIntencionDeAdopcion(user_4, asociacion_2, TipoMascota.MINIPIG, pref_3_asoc_2, pregs_3_asoc_2);
        em.persist(intencion_1);
        em.persist(intencion_2);
        em.persist(intencion_3);

        List<Respuesta> pref_4_asoc_1 = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(tamanio, "grande"), new Respuesta(pelo, "largo"), new Respuesta(cola, "larga"));
        pref_4_asoc_1.forEach(em::persist);
        List<Respuesta> pref_5_asoc_2 = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(castrada, "si"), new Respuesta(cantidadPatas, "tres"));
        pref_5_asoc_2.forEach(em::persist);
        List<Respuesta> pref_6_asoc_1 = Arrays.asList(new Respuesta(color, "marron"),
                new Respuesta(tamanio, "mediano"), new Respuesta(pelo, "corto"), new Respuesta(cola, "corta"));
        pref_6_asoc_1.forEach(em::persist);

        List<Respuesta> pregs_4_asoc_1 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "no"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        pregs_4_asoc_1.forEach(em::persist);
        List<Respuesta> pregs_5_asoc_2 = Arrays.asList(new Respuesta(con_patio, "no"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "no"),
                new Respuesta(duenio_buena_persona, "si"), new Respuesta(vacunada, "si"));
        pregs_5_asoc_2.forEach(em::persist);
        List<Respuesta> pregs_6_asoc_1 = Arrays.asList(new Respuesta(con_patio, "chico"),
                new Respuesta(otros_animales, "si"), new Respuesta(casa_grande, "si"),
                new Respuesta(alto_presupuesto, "si"), new Respuesta(atencion_8hs, "8hs"));
        pregs_6_asoc_1.forEach(em::persist);

        PublicacionIntencionDeAdopcion intencion_4 = new PublicacionIntencionDeAdopcion(user_5, asociacion_1, TipoMascota.GATO, pref_4_asoc_1, pregs_4_asoc_1);
        PublicacionIntencionDeAdopcion intencion_5 = new PublicacionIntencionDeAdopcion(user_5, asociacion_2, TipoMascota.PERRO, pref_5_asoc_2, pregs_5_asoc_2);
        PublicacionIntencionDeAdopcion intencion_6 = new PublicacionIntencionDeAdopcion(user_5, asociacion_1, TipoMascota.MINIPIG, pref_6_asoc_1, pregs_6_asoc_1);
        em.persist(intencion_4);
        em.persist(intencion_5);
        em.persist(intencion_6);





//voluntario automatico
        PublicacionService repo = new PublicacionService(em);
        repo.aceptarPendientes();

    }


}
