package com.dds.rescate.model;

import java.io.IOException;

import com.dds.rescate.exception.ValidadorException;
import com.dds.rescate.model.Enum.Sexo;
import com.dds.rescate.model.Enum.TipoMascota;

import java.util.ArrayList;
import java.util.List;

import static com.dds.rescate.service.ImageLoader.resizeImage;

public class Mascota {
    private TipoMascota tipoMascota;
    private String nombre;
    private String apodo;
    private String descripcion;
    private List<CaracteristicaMascota> caracteristicas = new ArrayList<>();
    private Asociacion asociacionRegistrada;
    private Sexo sexo;
    private boolean perdida;
    private List<Foto> fotos = new ArrayList<>();

    public Mascota(TipoMascota tipoMascota, String nombre, String apodo, String descripcion, Asociacion asociacionRegistrada,
                   List<CaracteristicaMascota> caracteristicas, Sexo sexo, String fotoMinima) throws ValidadorException {
        asociacionRegistrada.validarCaracteristicasAsociacion(caracteristicas);
        //TODO validar respuestas a las caracteristicas
        this.asociacionRegistrada = asociacionRegistrada;
        this.caracteristicas = caracteristicas;
        this.tipoMascota = tipoMascota;
        this.nombre = nombre;
        this.apodo = apodo;
        this.descripcion = descripcion;
        //this.caracteristicas = validarCaracteristicas(caracteristicas);
        this.sexo = sexo;
        this.perdida = false;
        this.fotos.add(new Foto(fotoMinima));
    }


    public Mascota() {}
    
    public List<CaracteristicaMascota> validarCaracteristicas(List<CaracteristicaMascota> caracteristicas) throws ValidadorException  {
    	
    	for (int i=0; i<caracteristicas.size(); i++)
        {
    		if(!existeCaracteristicaEnCatalogo(caracteristicas.get(i).getCaracteristica())) {
    			throw new ValidadorException("No existe la caracteristica " + caracteristicas.get(i).getCaracteristica() + " en el catalogo");
    		}
        }
    	
    	return caracteristicas;
    }
    
    public boolean existeCaracteristicaEnCatalogo(Caracteristica caracteristica) {
    	
    	return CatalogoCaracteristicas.getInstance().getCaracteristicas().contains(caracteristica);
    	
    }

    public boolean estaPerdida(){
        return this.perdida;
    }

    public void perder(){
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

    public List<Foto> getFotos() {
        return fotos;
    }

    public String getFotoString(){
        return this.fotos.get(0).nombreFoto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CaracteristicaMascota> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaMascota> caracteristica) {
        this.caracteristicas = caracteristica;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNombreAsociacionString(){
        return this.asociacionRegistrada.getNombre();
    }
    public String getSexoString(){return this.sexo.toString();}

    //Carga una imagen "imagen.jpg" de resources/ImgTemp
    //Se guarda estandarizada en resources/Imagenes
    //Identificada solo por el nombre, dentro de mascota
    public void cargarImagen(String nombre) {
        try {
            resizeImage(nombre);
            this.fotos.add(new Foto(nombre));
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

}
