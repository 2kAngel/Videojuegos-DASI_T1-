package es.upsa.dasi.videojuegos.gateway.quarkus.resources;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.dtos.UnidentifiedVideojuego;
import es.upsa.dasi.videojuegos.entity.Plataforma;
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
import java.util.stream.Collectors;

@Path("/games")
public class VideojuegosResource
{

    @Inject
    Service service;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideojuegos() throws VideojuegoException
    {
        return Response.ok()
                .entity( new GenericEntity< List<Videojuego> >( service.demandVideojuegos() ) {} )
                .build();
    }

    //----------------------------------------------------

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {
        Videojuego videojuego = service.demandVideojuegoById(id);
        Optional<Plataformas> optPlataformas = service.demandPlataformaVideojuego(id);

        Mappers mappers = new Mappers();
        FullVideojuego fullVideojuego = mappers.toFullVideojuego(videojuego);

        if(optPlataformas.isPresent()){
            Plataformas plataformas = optPlataformas.get();

            //<---> Aqui irían los list que puedes add a los videojuegos (reparto, programadores, ...)
            //este if sin meter nada realmente no tiene mucho sentido

        }

        return Response.ok()
                .entity(fullVideojuego)
                .build();

    }
    //----------------------------------------------------

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postVideojuego(UnidentifiedVideojuego unidentifiedVideojuego) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        Videojuego videojuego = service.demandInsertVideojuego( mappers.toVideojuego(unidentifiedVideojuego) );

        return Response.created( uriInfo.getBaseUriBuilder()
                        .path("/games/{id}")
                        .build( videojuego.id() )
                )
                .entity( videojuego )
                .build();
    }
    //----------------------------------------------------


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putVideojuegoById(@PathParam("id") String id, UnidentifiedVideojuego unidentifiedVideojuego) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        Videojuego videojuego = service.demandUpdateVideojuego( mappers.toVideojuego(unidentifiedVideojuego).withId(id) );

        return Response.noContent()
                .build();
    }
    //----------------------------------------------------

    @DELETE
    @Path("{id}")
    public Response deleteVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {
        service.demandDeleteVideojuego( id );

        return Response.noContent()
                .build();
    }
}
