package com.dds.rescate.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.dds.rescate.model.ApiMap;
import com.google.gson.Gson;

public class HogarApiService {

	public HogarApiService() {
		// TODO Auto-generated constructor stub
	}
	
	public void getHogares() {
    	HttpClient client = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.refugiosdds.com.ar/api/hogares?offset=4"))
    			.GET()
    			.header("Authorization", "Bearer LLKdf1u3YYrWpkaB4LVyUxkbsYKSPOHI0dyaffGRTHnnYO0AFBtrGf3nA3YL").build();
    	try {
            HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(respuesta.body());
            
            Gson gson = new Gson();
            ApiMap map = gson.fromJson(respuesta.body(), ApiMap.class);
            System.out.println(map);
          } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }   
    }

}
