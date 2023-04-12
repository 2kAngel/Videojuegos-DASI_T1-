package es.upsa.dasi.videojuegos.gateway.quarkus.resources;

import es.upsa.dasi.videojuegos.dtos.FullDesarrolladora;
import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.dtos.UnidentifiedDesarrolladora;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.gateway.quarkus.services.Service;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;

@Path("/developers")
public class DesarrolladorasResource
{
    @Inject
    Service service;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDesarrolladoras() throws VideojuegoException
    {
        return Response.ok()
                .entity( new GenericEntity<List<Desarrolladora>>( service.demandDesarrolladoras() ) {} )
                .build();
    }


    //----------------------------------------------------

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDesarrolladoraById(@PathParam("id") String id) throws VideojuegoException
    {
        Desarrolladora desarrolladora = service.demandDesarrolladoraById(id);
        Optional<Plataformas> optPlataformas = service.demandPlataformaVideojuego(id);

        Mappers mappers = new Mappers();
        FullDesarrolladora fullDesarrolladora  = mappers.toFullDesarrolladora(desarrolladora);

        if(optPlataformas.isPresent()){
            Plataformas plataformas = optPlataformas.get();

            //<---> Aqui irían los list que puedes add a los videojuegos (reparto, programadores, ...)
            //este if sin meter nada realmente no tiene mucho sentido

        }
        return Response.ok()
                .entity(fullDesarrolladora)
                .build();

    }

    //----------------------------------------------------

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDesarrolladora(UnidentifiedDesarrolladora unidentifiedDesarrolladora) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        Desarrolladora desarrolladora = service.demandInsertDesarrolladora( mappers.toDesarrolladora(unidentifiedDesarrolladora) );

        return Response.created( uriInfo.getBaseUriBuilder()
                        .path("/developers/{id}")
                        .build( desarrolladora.id() )
                )
                .entity( desarrolladora )
                .build();
    }

    //----------------------------------------------------

    @PUT
    @Path("/developers/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putDesarrolladoraById(@PathParam("id") String id, UnidentifiedDesarrolladora unidentifiedDesarrolladora) throws VideojuegoException {
        Mappers mappers = new Mappers();
        Desarrolladora desarrolladora = service.demandInsertDesarrolladora(mappers.toDesarrolladora(unidentifiedDesarrolladora).withId(id));

        return Response.noContent()
                .build();
    }

    //----------------------------------------------------

    @DELETE
    @Path("/developers/{id}")
    public Response deleteDesarrolladoraById(@PathParam("id") String id) throws VideojuegoException
    {
        service.demandDeleteDesarrolladora( id );

        return Response.noContent()
                .build();
    }

}
