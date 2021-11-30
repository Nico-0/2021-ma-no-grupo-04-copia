package com.dds.rescate.server;

import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Server extends HandlebarsTemplateEngine {

    public Server() {
        super();
        handlebars.registerHelpers(this);
        //https://jknack.github.io/handlebars.java/helpers.html

        //handlebars.registerHelpers(ConditionalHelpers.class);
        //https://github.com/jknack/handlebars.java/tree/master/handlebars/src/main/java/com/github/jknack/handlebars/helper
    }

    public static void main(String[] args) {
        Spark.port(getHerokuAssignedPort());
        DebugScreen.enableDebugScreen();
        Spark.staticFiles.location("/public");
        //Bootstrap.init();
        //Data.init();

        Router.configure();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 9000; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    // Example of a helper, write {{now}} in template file.
    public String now() {
        return new Date().toString();
    }

    // {{inc @index}}
    public String inc(int valor) {
        return Integer.toString(valor + 1);
    }
    // {{dec nro_pag}}
    public String dec(int valor) {
        return Integer.toString(valor - 1);
    }

    //robado del link de arriba ConditionalHelpers (que spark handlebars usa version vieja de handlebars)
    public CharSequence equals(final Object a, final Options options) throws IOException {
        Object b = options.param(0, null);
        boolean result = eq(a, b);
        if (options.tagType == TagType.SECTION) {
            return result ? options.fn() : options.inverse();
        }
        return result
                ? options.hash("yes", true)
                : options.hash("no", false);
    }

    protected boolean eq(final Object a, final Object b) {
        boolean value = Objects.equals(a, b);
        if (!value) {
            if (a instanceof Number && b instanceof Number) {
                // int vs double: 2 vs 2.0
                return ((Number) a).doubleValue() == ((Number) b).doubleValue();
            }
        }
        return value;
    }

}
