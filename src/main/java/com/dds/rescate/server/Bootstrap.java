package com.dds.rescate.server;

import com.dds.rescate.model.*;
import com.dds.rescate.service.PublicacionService;

public class Bootstrap {
    public static void init() {
        //aca crear las instancias en memoria

        PublicacionService instancia = PublicacionService.getInstance();

        Contacto contacto_1 = new Contacto(null, null, null, null, null);
        DatosPersonales datos_1 = new DatosPersonales("pepe", null,null,null,contacto_1);
        Contacto contacto_2 = new Contacto(null, null, null, null, null);
        DatosPersonales datos_2 = new DatosPersonales("pablo", null,null,null,contacto_2);

        Formulario formulario_1 = new Formulario(datos_1,null,"Un formulario",null);
        Formulario formulario_2 = new Formulario(datos_2,null,"Otro formulario",null);
        UsuarioDuenio user_1 = new UsuarioDuenio("pepedestroyer9000","@$&*#HFGG445d450",datos_1);
        UsuarioDuenio user_2 = new UsuarioDuenio("pablito15","1@3$5^7*9)h",datos_2);


        Asociacion asociacion_1 = new Asociacion("patitas al rescate",null,null,null);
        Asociacion asociacion_2 = new Asociacion("orejas rescatadas",null,null,null);


        instancia.generarPublicacion(formulario_1, user_1, asociacion_1);
        instancia.generarPublicacion(formulario_1, user_1, asociacion_2);
        instancia.generarPublicacion(formulario_2, user_2, asociacion_1);
        instancia.generarPublicacion(formulario_2, user_2, asociacion_2);

    }
}
