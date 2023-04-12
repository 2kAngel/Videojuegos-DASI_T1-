package es.upsa.dasi.videojuegos.gateway.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.dtos.ErrorMessage;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoNotFoundException;
import es.upsa.dasi.videojuegos.gateway.quarkus.daos.VideojuegosDao;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class VideojuegosDaoImpl implements VideojuegosDao
{

    //---------------------------------------------------------------
    @Override
    public List<Videojuego> requestVideojuegos() throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                         .build()
                                         .target("http://localhost:8081/games")
                                         .request(MediaType.APPLICATION_JSON_TYPE)
                                         .get();

        switch ( response.getStatus() )
        {

            case 200:   List<Videojuego> videojuegos = response.readEntity( new GenericType< List<Videojuego> >() {}  );
                return videojuegos;

            default:   ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    //---------------------------------------------------------------
    @Override
    public Videojuego requestVideojuegoById(String id) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                         .build()
                                         .target("http://localhost:8081/games/{id}")
                                         .resolveTemplate("id", id)
                                         .request(MediaType.APPLICATION_JSON_TYPE)
                                         .get();

        switch ( response.getStatus() )
        {
            case 200:   Videojuego videojuego = response.readEntity( Videojuego.class );
                return videojuego;

            case 404:   throw new VideojuegoNotFoundException(id);

            default:    ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }
    //---------------------------------------------------------------
    @Override
    public Videojuego requestInsertVideojuego(Videojuego videojuego) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                         .build()
                                         .target("http://localhost:8081/games")
                                         .request(MediaType.APPLICATION_JSON_TYPE)
                                         .post( Entity.json(videojuego) );
        switch ( response.getStatus() )
        {
            case 201: return response.readEntity( Videojuego.class );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }
    //---------------------------------------------------------------
    @Override
    public Videojuego requestUpdateVideojuego(Videojuego videojuego) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                         .build()
                                         .target("http://localhost:8081/games/{id}")
                                         .resolveTemplate("id", videojuego.id())
                                         .request(MediaType.APPLICATION_JSON_TYPE)
                                         .put( Entity.json(videojuego) );
        switch ( response.getStatus() )
        {
            case 204: return videojuego;

            case 404: throw new VideojuegoNotFoundException( videojuego.id() );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }
    //---------------------------------------------------------------
    @Override
    public void requestDeleteVideojuego(String id) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                         .build()
                                         .target("http://localhost:8081/games/{id}")
                                         .resolveTemplate("id", id)
                                         .request()
                                         .delete(  );
        switch ( response.getStatus() )
        {
            case 204: return ;

            case 404: throw new VideojuegoNotFoundException( id );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }
}
