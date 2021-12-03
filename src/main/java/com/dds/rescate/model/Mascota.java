package com.dds.rescate.model;

import java.io.IOException;

import com.dds.rescate.exception.ValidadorException;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.dds.rescate.service.ImageLoader.resizeImage;

@Entity
public class Mascota {
    @Id
    @GeneratedValue
    private int ID;

    @Enumerated(EnumType.STRING)
    private TipoMascota tipoMascota;

    private String nombre;
    private String apodo;
    private String descripcion;

    @OneToMany//Todas las respuestas son unicas, creadas al instanciar la mascota
    @JoinColumn(name = "FK_mascota")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Respuesta> caracteristicas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "asoc_id")
    private Asociacion asociacionRegistrada;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    String id_chapita;

    @Type(type="yes_no")
    private boolean perdida;

    @ElementCollection//(fetch = FetchType.EAGER)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name = "Mascota_ID")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> fotos = new ArrayList<>();

    //seria util tener la referencia a la publicacion, para poder cancelar la publicacion desde la mascota
    public boolean publicada;

    public Mascota(TipoMascota tipoMascota, String nombre, String apodo, String descripcion, Asociacion asociacionRegistrada,
                   List<Respuesta> caracteristicas, Sexo sexo, String fotoMinima) throws ValidadorException {
        asociacionRegistrada.validarCaracteristicasAsociacion(caracteristicas);
        //validar respuestas a las caracteristicas, en el caso de implementar el ingreso manual de estas
        this.asociacionRegistrada = asociacionRegistrada;
        this.caracteristicas = caracteristicas;
        this.tipoMascota = tipoMascota;
        this.nombre = nombre;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.sexo = sexo;
        this.perdida = false;
        this.fotos.add(fotoMinima);
        this.publicada = false;
        this.id_chapita = nombre+apodo+Calendar.getInstance().get(Calendar.MILLISECOND);
    }


    public Mascota() {}
    
    public List<Respuesta> validarCaracteristicas(List<Respuesta> caracteristicas) throws ValidadorException  {
    	
    	for (int i=0; i<caracteristicas.size(); i++)
        {
    		if(!existeCaracteristicaEnCatalogo(caracteristicas.get(i).getCaracteristica())) {
    			throw new ValidadorException("No existe la caracteristica " + caracteristicas.get(i).getCaracteristica() + " en el catalogo");
    		}
        }
    	
    	return caracteristicas;
    }
    
    public boolean existeCaracteristicaEnCatalogo(PreguntaCaracteristica caracteristica) {
    	
    	return CatalogoCaracteristicas.getInstance().getCaracteristicas().contains(caracteristica);
    	
    }

    public boolean estaPerdida(){
        return this.perdida;
    }

    public void perder(){
        if(publicada){
            throw new RuntimeException("En este universo no puede perder a su mascota si se encuentra publicada");
        }
        this.perdida = true;
    }

    public void recuperar(){
        this.perdida = false;
    }

    public void recuperarYNotificar(Contacto contacto, String mensaje){
        this.recuperar();
        contacto.notificarTodoMedio(contacto.getNombre() + ": " + mensaje);
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public String getFotoString(){
        return this.fotos.get(0);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Respuesta> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Respuesta> caracteristica) {
        this.caracteristicas = caracteristica;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getChapita(){
        return id_chapita;
    }

    public String getNombreAsociacionString(){
        return this.asociacionRegistrada.getNombre();
    }
    public String getSexoString(){return this.sexo.toString();}

    public String getIdMascotaString(){
        return Integer.toString(ID);
    }

    //Carga una imagen "imagen.jpg" de resources/ImgTemp
    //Se guarda estandarizada en resources/Imagenes
    //Identificada solo por el nombre, dentro de mascota
    public void cargarImagen(String nombre) {
        try {
            resizeImage(nombre);
            this.fotos.add(nombre);
            System.out.println("Se carga la foto " + nombre);
        }
        catch (IOException ex){
            System.out.println("No existe la imagen " + nombre);
        }

    }

    @Override
    public String toString() {
        return "Mascota{" +
                "tipoMascota=" + tipoMascota +
                ", nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", sexo=" + sexo +
                ", fotos=" + fotos +
                '}';
    }

    public UsuarioDuenio getDuenio(EntityManager entityManager){
        List<UsuarioDuenio> usuarios = entityManager.createQuery("from UsuarioDuenio", UsuarioDuenio.class)
                .getResultList();
        return usuarios.stream().filter(u -> u.getMascotas().contains(this)).collect(Collectors.toList()).get(0);
    }

}
