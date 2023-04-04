package es.upsa.dasi.videojuegos.quarkus.resources;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.quarkus.services.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/developers")
@Produces(MediaType.APPLICATION_JSON)
public class DesarrolladoraResource {

    @Inject
    Service service;

    @GET
    public Response getDesarrolladoras() throws VideojuegoException
    {
        return Response.ok()
                .entity( service.findDesarrolladoras() )
                .build();
    }

    @GET
    @Path("{id}")
    public Response getDesarrolladoraById(@PathParam("id") String id) throws VideojuegoException
    {
        return service.findDesarrolladoraById(id)
                .map( person -> Response.ok(person) )
                .orElse( Response.status(Response.Status.NOT_FOUND) )
                .build();
    }
}