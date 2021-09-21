package com.dds.rescate.server;

import com.dds.rescate.controllers.MuroPublicaciones;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    public static void configure() {

        HandlebarsTemplateEngine engine =
                new HandlebarsTemplateEngine();


        MuroPublicaciones muroPublicaciones = new MuroPublicaciones();

        Spark.get("/", MuroPublicaciones::show, engine);
    }
}
