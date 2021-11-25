package com.dds.rescate.util;


import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity
public class GeneradorID {

    @Id
    int id = 0;

    public int ultimoID;

    public GeneradorID(){
        id = 0;
        ultimoID = 0;
    }
}
