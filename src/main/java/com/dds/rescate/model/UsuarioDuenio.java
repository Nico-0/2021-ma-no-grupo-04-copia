package com.dds.rescate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import javax.persistence.*;

@Entity
public class UsuarioDuenio extends Usuario{

    @OneToMany//(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //una mascota puede cambiar de dueño pero siempre va a estar en uno solo
    @JoinColumn(name = "FK_duenio")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Mascota> mascotas = new ArrayList<>();
    @OneToOne //DatosPersonales es unico para cada usuario
    private DatosPersonales perfil;

    public UsuarioDuenio(String username, String password, DatosPersonales perfil) {
        super(username, password);
        this.perfil = perfil;
    }
    private UsuarioDuenio(){

    }

    //Getteers y Setters
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public List<Mascota> getMascotasSinPublicar() {
        return getMascotas().stream().filter(m -> !m.publicada).collect(Collectors.toList());
    }
    public List<Mascota> getMascotasComprometidas() {
        return getMascotas().stream().filter(m -> m.publicada).collect(Collectors.toList());
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public DatosPersonales getPerfil() {
        return perfil;
    }

    public Contacto getContacto(){
        return getPerfil().getContactos().get(0);
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

    public String getNombre(){
        return this.perfil.getNombre();
    }
    @Override
    public String getNombreCompleto(){
        return this.perfil.getNombre() +"_"+ this.perfil.getApellido();
    }
    @Override
    public String getTipo(){
        return "comun";
    }

    public void agregarMascota(Mascota nuevaMascota){
        this.mascotas.add(nuevaMascota);
    }
}
