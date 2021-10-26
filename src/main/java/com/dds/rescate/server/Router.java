package com.dds.rescate.server;

import com.dds.rescate.controllers.*;
import spark.ModelAndView;
import spark.Route;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static com.dds.rescate.controllers.LoginController.verificarLogin;

public class Router {

    static EntityManagerFactory entityManagerFactory;

    public static void configure() {

        entityManagerFactory = Persistence.createEntityManagerFactory("db");

        HandlebarsTemplateEngine engine = new Server();
        /* 		.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
				import spark.utils.BooleanHelper;
         */


        Spark.before((request, response)-> {
            if(request.requestMethod().equals("GET") && !request.pathInfo().equals("/") && !request.pathInfo().equals("/muro") && verificarLogin(request).equals("null")) {
                response.redirect("/");
            }
            //TODO verificar que el usuario loginado exista en el repositorio de usuarios
        });

        /*
        Spark.after((request, response) -> {

        });*/

        Spark.get("/", HomeController::home, engine);

        Spark.get("/muro", MuroPublicaciones::muro, engine);
        Spark.get("/recomendador", RecomendadorController::recomendador, engine);
        Spark.get("/aprobar", Aprobar::aprobar, engine);
        Spark.get("/gestion", Gestion::gestion, engine);
        Spark.get("/perfil", Perfil::perfil, engine);
        Spark.get("/mis_mascotas", MisMascotas::show, engine);
        Spark.get("/mis_publicaciones", MisPublicaciones::show, engine);

        Spark.post("/", LoginController::login);

        Spark.post("/logout", LoginController::logout);

        Spark.post("/registro/comun", LoginController::comun);
        Spark.post("/registro/voluntario", LoginController::voluntario);
        Spark.post("/registro/admin", LoginController::admin);

        Spark.get("/registro/sucess", LoginController::sucess, engine);

        Spark.post("/recomendador/regenerar", RecomendadorController::recomendar);

        Spark.get("/publicaciones", (req, res) -> "Lista de publicaciones aqui");
        Spark.get("/publicaciones/:id", TemplWithTransaction(Publicaciones::show), engine);

        Spark.post("/publicaciones/:id/adoptar", Publicaciones::adoptar);
        Spark.post("/publicaciones/:id/recuperar", Publicaciones::recuperar);
        Spark.post("/publicaciones/:id/finalizar", Publicaciones::finalizar);

        Spark.post("/mascotas/notificar", (req, res) -> "Si existe la chapita, entonces el dueño ha sido notificado. El dueño se acercará a su domicilio (a retirar el animal) a la brevedad.");

        Spark.get("/usuarios/:id_user/recomendaciones", RecomendadorController::get_json);


    }

    private static TemplateViewRoute TemplWithTransaction(WithTransaction<ModelAndView> fn) {
        EntityManager em = entityManagerFactory.createEntityManager();

        TemplateViewRoute r = (req, res) -> {

            em.getTransaction().begin();

            ModelAndView result = fn.method(req, res, em);
            em.getTransaction().commit();
            return result;
            //TODO agregar catch de exception
        };

        em.close();

        return r;
    }
    private static Route RouteWithTransaction(WithTransaction<Object> fn) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Route r = (req, res) -> {
            em.getTransaction().begin();

            Object result = fn.method(req, res, em);
            em.getTransaction().commit();
            return result;
            //TODO agregar catch de exception
        };
        em.close();

        return r;
    }
}
