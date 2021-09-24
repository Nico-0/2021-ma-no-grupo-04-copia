package com.dds.rescate.server;

import com.dds.rescate.controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    public static void configure() {

        HandlebarsTemplateEngine engine =
                new HandlebarsTemplateEngine();



        Spark.get("/", HomeController::home, engine);

        Spark.get("/muro", MuroPublicaciones::muro, engine);
        Spark.get("/recomendador", Recomendador::recomendador, engine);
        Spark.get("/aprobar", Aprobar::aprobar, engine);
        Spark.get("/gestion", Gestion::gestion, engine);
        Spark.get("/perfil", Perfil::perfil, engine);
        Spark.get("/mis_mascotas", MisMascotas::show, engine);
        Spark.get("/mis_publicaciones", MisPublicaciones::show, engine);


    }
}
