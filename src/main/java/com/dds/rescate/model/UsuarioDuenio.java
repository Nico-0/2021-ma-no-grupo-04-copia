package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDuenio extends Usuario{

    private List<Mascota> mascotas = new ArrayList<>();
    private String nombre;
    private String apellido;
    private Date nacimiento;
    private String documento;
    private List<Contacto> datosContacto = new ArrayList<>();

    public UsuarioDuenio(String username, String password, String nombre, String apellido, Date nacimiento, String documento, Contacto contactoMinimo) {
        super(username, password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.documento = documento;
        this.datosContacto.add(contactoMinimo);
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void registrarMascota(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    public long getNumMascotasPerdidas(){
        return this.mascotas.stream().filter(mascota -> mascota.estaPerdida()).count();
    }



}
