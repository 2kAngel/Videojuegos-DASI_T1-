package es.upsa.dasi.videojuegos.quarkus.resources;

import es.upsa.dasi.videojuegos.dtos.UnidentifiedDesarrolladora;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.quarkus.services.Service;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/developers")
@Produces(MediaType.APPLICATION_JSON)
public class DesarrolladoraResource {

    @Inject
    Service service;

    @Context
    UriInfo uriInfo;

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

    @PUT
    @Path("{id}")
    public Response updateDesarrolladoraById(@PathParam("id") String id, UnidentifiedDesarrolladora unidentifiedDesarrolladora) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        service.updateDesarrolladoraById( mappers.toDesarrolladora(unidentifiedDesarrolladora).withId(id) );
        return Response.noContent()
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteDesarrolladoraById(@PathParam("id") String id) throws VideojuegoException
    {
        service.deleteDesarrolladoraById(id);
        return Response.noContent()
                .build();
    }

    @POST
    public Response insertDesarrolladora(UnidentifiedDesarrolladora unidentifiedDesarrolladora) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        Desarrolladora newDesarrolladora = service.createDesarrolladora( mappers.toDesarrolladora(unidentifiedDesarrolladora) );
        URI newDesarrolladoraUri = uriInfo.getBaseUriBuilder()
                .path("/developers/{id}")
                .build( newDesarrolladora.id() );
        return Response.created(newDesarrolladoraUri)
                .entity(newDesarrolladora)
                .build();
    }
}