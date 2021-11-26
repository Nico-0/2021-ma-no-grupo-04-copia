package com.dds.rescate.server.jobs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static com.dds.rescate.controllers.RecomendadorController.recomendarTodos;

public class Recomendar {
    public static void main(String[] args){
        System.out.println("Se recomienda a todos los usuarios");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        recomendarTodos(em);
        em.getTransaction().commit();

        System.out.println("chau soy el programa ejecutable");
        System.exit(0);
    }
}