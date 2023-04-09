package es.upsa.dasi.videojuegos.plataformas.quarkus.resources;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.plataformas.quarkus.services.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/plataformas")
public class PlataformaResource {

    @Inject
    Service service;

    @GET
    public Response getPlataformas() throws VideojuegoException {
        return Response.ok()
                .entity(  service.getPlataformas() )
                .build();
    }
}