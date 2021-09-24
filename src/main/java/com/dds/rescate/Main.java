package com.dds.rescate;

import com.dds.rescate.model.*;
import com.dds.rescate.service.GeneradorUsuario;
import com.dds.rescate.service.HogarApiService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola");
        /*
        Usuario edu = new Usuario("eduRios","edu7298765");
        GeneradorUsuario generadorUsuario = new GeneradorUsuario();
        generadorUsuario.registrarUsuario(edu);
        
        Mascota mascota = new Mascota();
        
        edu.registrarMascota(mascota);
       
        System.out.println("La mascota es: " + edu.getMascotas().size());
        */
        /*
        Caracteristica color;
        CaracteristicaMascota colorM;
        List<CaracteristicaMascota> caracteristicas = new ArrayList<>();
        color = new Caracteristica("Color");
        colorM = new CaracteristicaMascota(color, "naranja");
        CatalogoCaracteristicas.getInstance().agregarCaracteristica(color);
        caracteristicas.add(colorM);
    	Mascota mascota = new Mascota("Perro", "Jey", "Jey", "Pitbull", caracteristicas, Sexo.MACHO);
    	mascota.cargarImagen("mascota.jpg");
        */
    	//System.out.println("La mascota es: " + mascota.prueba2());
    	//HogarApiService api = new HogarApiService();
    	//api.getHogares();

    }
}