package es.upsa.dasi.videojuegos.gateway.quarkus.resources;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.entity.Plataforma;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.gateway.quarkus.services.Service;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
