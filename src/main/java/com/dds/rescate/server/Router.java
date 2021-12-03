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
            if(request.requestMethod().equals("GET") && !request.pathInfo().equals("/") && !request.pathInfo().startsWith("/muro") && !request.pathInfo().startsWith("/error") && verificarLogin(request).equals("null")) {
                response.redirect("/");
            }
            //aca solo se verifica que exista el usuario al momento del login
        });

        /*
        Spark.after((request, response) -> {

        });*/

        Spark.get("/", HomeController::home, engine);

        Spark.get("/muro", RouteWithTransaction(MuroPublicaciones::muroSinPag));
        Spark.get("/muro/:nro_pag", TemplWithTransaction(MuroPublicaciones::muro), engine);
        Spark.get("/recomendador", TemplWithTransaction(RecomendadorController::recomendador), engine);
        Spark.get("/aprobar", Aprobar::aprobar, engine);
        Spark.get("/gestion", TemplWithTransaction(Gestion::gestion), engine);
        Spark.get("/perfil", TemplWithTransaction(Perfil::perfil), engine);
        Spark.get("/mis_mascotas", TemplWithTransaction(MisMascotas::show), engine);
        Spark.get("/mis_publicaciones", TemplWithTransaction(MisPublicaciones::show), engine);
        Spark.get("/confirmacion", TemplWithTransaction(Confirmaciones::show), engine);

        Spark.post("/", RouteWithTransaction(LoginController::login));

        Spark.post("/logout", LoginController::logout);

        Spark.post("/registro/comun", RouteWithTransaction(LoginController::comun));
        Spark.post("/registro/voluntario", RouteWithTransaction(LoginController::voluntario));
        Spark.post("/registro/admin", RouteWithTransaction(LoginController::admin));

        Spark.get("/registro/sucess", LoginController::sucess, engine);

        Spark.post("/recomendador/regenerar", RouteWithTransaction(RecomendadorController::recomendar));
        Spark.post("/recomendador/:id_user/regenerar", RouteWithTransaction(RecomendadorController::recomendarSingle));

        Spark.get("/publicaciones", (req, res) -> "Lista de publicaciones aqui");
        Spark.get("/publicaciones/:id", TemplWithTransaction(Publicaciones::show), engine);

        Spark.post("/publicaciones/:id/adoptar", RouteWithTransaction(Publicaciones::adoptar));
        Spark.post("/publicaciones/:id/recuperar", RouteWithTransaction(Publicaciones::recuperar));
        Spark.post("/publicaciones/:id/finalizar", RouteWithTransaction(Publicaciones::finalizar));

        Spark.post("/publicaciones/:id/cancelarReserva", RouteWithTransaction(Publicaciones::cancelarReserva));
        Spark.post("/publicaciones/:id/aceptar", RouteWithTransaction(Publicaciones::aceptar));


        Spark.get("/usuarios/:id_user/recomendaciones", RouteWithTransaction(RecomendadorController::get_json));

        Spark.post("/datos1", RouteWithTransaction(HomeController::datos_base));
        Spark.post("/datos2", RouteWithTransaction(HomeController::datos_extra));
        Spark.post("/borrar", RouteWithTransaction(HomeController::borrar_datos));


        Spark.post("/mis_mascotas", RouteWithTransaction(MisMascotas::registrarGenerica));
        Spark.post("/mascotas/:id/publicarAdopcion", RouteWithTransaction(MisMascotas::darAdopcion));

        Spark.post("/publicaciones/perdidaGenerica", RouteWithTransaction(MisPublicaciones::publicarPerdidaGenerica));
        Spark.post("/publicaciones/intencionGenerica", RouteWithTransaction(MisPublicaciones::publicarIntencionGenerica));

        Spark.get("/error/usuario", (req, res) -> { res.status(401);
            return "<h1>No existe el usuario</h1> <a href=../../>Volver al inicio</a>";});
        Spark.get("/error/contrasenia", (req, res) -> { res.status(401);
            return "<h1>Contraseña incorrecta</h1> <a href=../../>Volver al inicio</a>";});

        Spark.post("/mascotas/:id/perdida", RouteWithTransaction(MisMascotas::perdida));
        Spark.post("/mascotas/notificar", RouteWithTransaction(MisPublicaciones::notificar));

        Spark.post("/mascotas/:id/aceptar", RouteWithTransaction(MisMascotas::aceptar));
        Spark.post("/mascotas/:id/rechazar", RouteWithTransaction(MisMascotas::rechazar));
    }

    private static TemplateViewRoute  TemplWithTransaction(WithTransaction<ModelAndView> fn) {
        TemplateViewRoute r = (req, res) -> {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            try {
                ModelAndView result = fn.method(req, res, em);
                em.getTransaction().commit();
                return result;
            } catch (Exception ex) {
                em.getTransaction().rollback();
                throw ex;
            } finally {
                em.close();
            }
        };
        return r;
    }
    private static Route RouteWithTransaction(WithTransaction<Object> fn) {
        Route r = (req, res) -> {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            try {
                Object result = fn.method(req, res, em);
                em.getTransaction().commit();
                return result;
            } catch (Exception ex) {
                em.getTransaction().rollback();
                throw ex;
            } finally {
                em.close();
            }
        };
        return r;
    }
}
