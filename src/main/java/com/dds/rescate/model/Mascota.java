package com.dds.rescate.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Mascota {
    private String tipoMascota;
    private String nombre;
    private String apodo;
    private String descripcion;
    private List<CaracteristicaMascota> caracteristicas;
    private Sexo sexo;
    private boolean perdida;

    public Mascota(String tipoMascota, String nombre, String apodo, String descripcion, List<CaracteristicaMascota> caracteristicas, Sexo sexo) throws Exception {
        this.tipoMascota = tipoMascota;
        this.nombre = nombre;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.caracteristicas = validarCaracteristicas(caracteristicas);
        this.sexo = sexo;
        this.perdida = false;
    }
    
    public Mascota() {}
    
    public List<CaracteristicaMascota> validarCaracteristicas(List<CaracteristicaMascota> caracteristicas) throws Exception  {
    	
    	for (int i=0; i<caracteristicas.size(); i++)
        {
    		if(!existeCaracteristicaEnCatalogo(caracteristicas.get(i).getCaracteristica())) {
    			throw new Exception("No existe la caracteristica " + caracteristicas.get(i).getCaracteristica() + " en el catalogo");
    		}
        };
    	
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

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
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
}
