package com.dds.rescate.model;

import java.util.ArrayList;
import java.util.List;

import com.dds.rescate.service.PublicacionService;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UsuarioDuenio extends Usuario{

    @OneToMany
    @JoinColumn(name = "FK_duenio")
    private List<Mascota> mascotas = new ArrayList<>();
    @OneToOne
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

    public void publicarMascotaPerdida(Formulario formulario) {
    	PublicacionService.getInstance().generarPublicacion(formulario, this, asociacion);
    }

    public String getNombre(){
        return this.perfil.getNombre();
    }
    @Override
    public String getTipo(){
        return "comun";
    }

    public void agregarMascota(Mascota nuevaMascota){
        this.mascotas.add(nuevaMascota);
    }
}
