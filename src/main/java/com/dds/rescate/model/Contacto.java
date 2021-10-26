package com.dds.rescate.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contacto {
    @Id
    @GeneratedValue
    private int id;
    public String nombre;
    public String apellido;
    public String email;
    public Integer telefono;
    @Transient
    public List<Notificador> formasNotificacion = new ArrayList<>();

    public Contacto(String nombre, String apellido, String email, Notificador notificadorMinimo, Integer telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.formasNotificacion.add(notificadorMinimo);
    }

    //Getters ySetters
    public String getNombre(){
        return this.nombre;
    }

    public String getEmail() {
        return email;
    }

    public List<Notificador> getFormasNotificacion() {
        return formasNotificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFormasNotificacion(List<Notificador> formasNotificacion) {
        this.formasNotificacion = formasNotificacion;
    }

    //Metodos
    public void notificarTodoMedio(String mensaje){
        formasNotificacion.forEach(notificador -> notificador.notificar(mensaje));
    }

    public String getContactoString(){
        return nombre + " " + apellido + ", " + email + ", " + telefono;
    }
}
