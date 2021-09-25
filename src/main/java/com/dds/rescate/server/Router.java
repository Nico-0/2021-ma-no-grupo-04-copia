package com.dds.rescate.server;

import com.dds.rescate.controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static com.dds.rescate.controllers.LoginController.verificarLogin;

public class Router {
    public static void configure() {
        HandlebarsTemplateEngine engine =
                new HandlebarsTemplateEngine();

        Spark.before((request, response)-> {
            if(request.requestMethod().equals("GET") && !request.pathInfo().equals("/") && verificarLogin(request).equals("null")) {
                response.redirect("/");
            }
            //TODO verificar que el usuario loginado exista en el repositorio de usuarios
        });
/* para persistencia
        Spark.after((request, response) -> {
            PerThreadEntityManagers.getEntityManager();
            PerThreadEntityManagers.closeEntityManager();
        });
*/
        Spark.get("/", HomeController::home, engine);

        Spark.get("/muro", MuroPublicaciones::muro, engine);
        Spark.get("/recomendador", Recomendador::recomendador, engine);
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
    }
}
