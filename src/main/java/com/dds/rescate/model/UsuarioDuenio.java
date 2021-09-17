package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDuenio extends Usuario{

    private List<Mascota> mascotas = new ArrayList<>();
    private DatosPersonales perfil;

    public UsuarioDuenio(String username, String password, DatosPersonales perfil) {
        super(username, password);
        this.perfil = perfil;
    }

    //Getteers y Setters
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public DatosPersonales getPerfil() {
        return perfil;
    }

    public void setPerfil(DatosPersonales perfil) {
        this.perfil = perfil;
    }

    //Metodos
    public void registrarMascota(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    public long getNumMascotasPerdidas(){
        return this.mascotas.stream().filter(mascota -> mascota.estaPerdida()).count();
    }



}
