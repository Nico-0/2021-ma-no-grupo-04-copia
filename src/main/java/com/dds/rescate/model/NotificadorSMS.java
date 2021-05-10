package com.dds.rescate.model;

public class NotificadorSMS implements Notificador{

    public static int vecesNotificado = 0;

    public void notificar(String mensaje){
        vecesNotificado++;
        System.out.println("enviar sms " + vecesNotificado + ": " + mensaje);
    }
}
