package com.dds.rescate.model;

public class NotificadorWpp implements Notificador{

    public void notificar(String mensaje){
        System.out.println("enviar WhatsApp");
    }
}
