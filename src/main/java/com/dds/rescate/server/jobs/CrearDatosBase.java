package com.dds.rescate.server.jobs;

import com.dds.rescate.server.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CrearDatosBase {
    public static void main(String[] args) {
        System.out.println("Se cargan los datos base");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Data.init(em);
        em.getTransaction().commit();

        System.out.println("chau soy el programa ejecutable");
        System.exit(0);
    }
}
