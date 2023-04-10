package es.upsa.dasi.resources;

import es.upsa.dasi.services.Service;
import es.upsa.dasi.videojuegos.dtos.UnidentifiedVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/games")
@RequestScoped
public class VideojuegosResource {

    @Inject
    Service service;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideojuegos() throws VideojuegoException {

        return  Response.ok()
                .entity( new GenericEntity<List<Videojuego>>(service.findVideojuegos()){})
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideojuegoById( @PathParam("id") String id ) throws VideojuegoException
    {
        Optional<Videojuego> optional = service.findVideojuegoById(id);
        if ( optional.isPresent() )
        {
            return Response.ok()
                    .entity( optional.get() )
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }




    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateVideojuegoById(@PathParam("id") String id, UnidentifiedVideojuego unidentifiedVideojuego) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        Videojuego videojuego = mappers.toVideojuego(unidentifiedVideojuego).withId(id);
        service.updateVideojuego(videojuego);
        return Response.noContent()
                .build();
//        return Response.status(Response.Status.NO_CONTENT)
//                       .build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {
        service.deleteVideojuegoById(id);
        return Response.noContent()
                .build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVideojuego( UnidentifiedVideojuego unidentifiedVideojuego ) throws VideojuegoException
    {
        Mappers mappers = new Mappers();
        Videojuego videojuego = service.insertVideojuego( mappers.toVideojuego(unidentifiedVideojuego) );
        URI uriNewPelicula = uriInfo.getBaseUriBuilder()
                .path("/games/{id}")
                .build(videojuego.id());

        return Response.created(uriNewPelicula)
                .entity( videojuego )
                .build();
    }
}