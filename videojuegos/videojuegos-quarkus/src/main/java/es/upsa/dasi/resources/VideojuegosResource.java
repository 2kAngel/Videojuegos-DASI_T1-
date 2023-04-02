package es.upsa.dasi.resources;

import es.upsa.dasi.services.Service;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/games")
@RequestScoped
public class VideojuegosResource {

    @Inject
    Service service;

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
    public Response getPeliculaById( @PathParam("id") String id ) throws VideojuegoException
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
}