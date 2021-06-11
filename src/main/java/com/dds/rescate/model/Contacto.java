package com.dds.rescate.model;
import java.util.ArrayList;
import java.util.List;

public class Contacto {

    public String nombre;
    public String apellido;
    public String email;

    public List<Notificador> formasNotificacion = new ArrayList<>();

    public Contacto(String nombre, String apellido, String email, Notificador notificadorMinimo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.formasNotificacion.add(notificadorMinimo);
    }

    public void notificarTodoMedio(String mensaje){
        formasNotificacion.forEach(notificador -> notificador.notificar(mensaje));
    }

    public String getNombre(){
        return this.nombre;
    }
}
