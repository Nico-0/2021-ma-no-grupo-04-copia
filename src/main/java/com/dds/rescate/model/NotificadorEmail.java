package com.dds.rescate.model;

public class NotificadorEmail implements Notificador{

    public void notificar(String mensaje){
        System.out.println("enviar email");
    }

}
