package com.dds.rescate.model;


import java.util.List;

public class Recomendacion {

    private PublicacionAdopcion adopcion;
    private int cantidadCaracteristicas;
    private int cantidadRequerimientos;
    private int total;

    public PublicacionAdopcion getAdopcion(){
        return adopcion;
    }

    public List<CaracteristicaMascota> getCaracteristicas(){
        return getAdopcion().getMascota().getCaracteristicas();
    }

    public List<CaracteristicaMascota> getRequerimientos(){
        return getAdopcion().getRequerimientos();
    }

    public String getCantidadCaracteristicas(){
        return Integer.toString(cantidadCaracteristicas);
    }

    public String getCantidadRequerimientos(){
        return Integer.toString(cantidadRequerimientos);
    }

    public String getTotal(){
        return Integer.toString(total);
    }

    public Recomendacion(PublicacionAdopcion adopcion, int cantidadCaracteristicas, int cantidadRequerimientos){
        this.adopcion = adopcion;
        this.cantidadCaracteristicas = cantidadCaracteristicas;
        this.cantidadRequerimientos = cantidadRequerimientos;
        this.total = cantidadCaracteristicas + cantidadRequerimientos;
    }



}
