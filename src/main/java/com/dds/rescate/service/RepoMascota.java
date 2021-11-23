package com.dds.rescate.service;

import com.dds.rescate.model.Mascota;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoMascota {

    public RepoMascota() {
    }
    private EntityManager entityManager;

    public RepoMascota(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Mascota> getMascotas() {
        return entityManager.createQuery("from Mascota", Mascota.class).getResultList();
    }

    public Mascota getMascotaByID(int id_mascota){
        return entityManager.createQuery("from Mascota m where m.ID = ?1", Mascota.class)
                .setParameter(1, id_mascota)
                .getSingleResult();
    }

}
