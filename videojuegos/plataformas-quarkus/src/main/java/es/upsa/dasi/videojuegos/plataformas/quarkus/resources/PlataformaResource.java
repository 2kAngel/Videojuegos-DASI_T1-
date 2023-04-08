package es.upsa.dasi.videojuegos.plataformas.quarkus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/plataformas")
public class PlataformaResource {

    @GET
    public Response getPlataformas()
    {
        return null;
    }
}