package com.dds.rescate.service;

import com.dds.rescate.model.ApiMap;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class HogarApiService {

	//https://app.swaggerhub.com/apis-docs/ezequieloscarescobar/hogares-transito-mascotas/1.0-oas3#/

	private Client client;

    public HogarApiService() {
        //  Auto-generated constructor stub
    }
    /*
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
            //  Auto-generated catch block
            e.printStackTrace();
          }
    }
*/
	public void getHogares() {
		this.client = Client.create();

		WebResource.Builder builder = this.client.resource("https://api.refugiosdds.com.ar/api/hogares?offset=4")
		    							.header("Content-Type", "application/json;charset=UTF-8")
										.header("Authorization", "Bearer LLKdf1u3YYrWpkaB4LVyUxkbsYKSPOHI0dyaffGRTHnnYO0AFBtrGf3nA3YL")
								 		.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		String json = response.getEntity(String.class);

		System.out.println(json);
            
		Gson gson = new Gson();
		ApiMap map = gson.fromJson(json, ApiMap.class);
		System.out.println(map);

    }

}
