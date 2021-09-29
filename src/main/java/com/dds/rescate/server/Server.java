package com.dds.rescate.server;

import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Date;

public class Server extends HandlebarsTemplateEngine {

    public Server() {
        super();
        handlebars.registerHelpers(this);
        //https://jknack.github.io/handlebars.java/helpers.html
    }

    public static void main(String[] args) {
        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Spark.staticFiles.location("/public");
        Bootstrap.init();
        Data.init();

        Router.configure();
    }


    // Example of a helper, write {{now}} in template file.
    public String now() {
        return new Date().toString();
    }

    // {{inc @index}}
    public String inc(int valor) {
        return Integer.toString(valor + 1);
    }


}
