package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dds.rescate.service.PublicacionService;

public class UsuarioDuenio extends Usuario{

    private List<Mascota> mascotas = new ArrayList<>();
    private DatosPersonales perfil;

    public UsuarioDuenio(String username, String password, DatosPersonales perfil) {
        super(username, password);
        this.perfil = perfil;
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


    public void publicarMascotaPerdida(Formulario formulario) {
    	PublicacionService.getInstance().generarPublicacion(formulario);
    }

}
