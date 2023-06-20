package com.example.apipreview;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/chuck-norris")
public class ChuckNorrisResource {
    private static final String API_URL = "https://api.chucknorris.io/jokes/random";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJoke() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(API_URL)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(String.class);
        } else {
            return "Error sending GET request. Response code: " + response.getStatus();
        }
    }
}