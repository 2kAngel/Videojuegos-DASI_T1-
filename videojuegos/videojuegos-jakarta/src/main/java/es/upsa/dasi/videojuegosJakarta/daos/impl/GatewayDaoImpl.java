package es.upsa.dasi.videojuegosJakarta.daos.impl;

import es.upsa.dasi.videojuegos.dtos.ErrorMessage;
import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoNotFoundException;
import es.upsa.dasi.videojuegos.mappers.Mappers;
import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegosJakarta.daos.Gatewaydao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Named("dao")
public class GatewayDaoImpl implements Gatewaydao {
    @Override
    public List<Videojuego> requestVideojuegos() throws VideojuegoException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8084/games")
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

    @Override
    public FullVideojuego requestVideojuegoById(String id) throws VideojuegoException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8084/games/{id}")
                .resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        switch ( response.getStatus() )
        {
            case 200:   FullVideojuego videojuego = response.readEntity( FullVideojuego.class );
                return videojuego;

            case 404:   throw new VideojuegoNotFoundException(id);

            default:   ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public Videojuego requestInsertVideojuego(Videojuego videojuego) throws VideojuegoException {

        Mappers mappers = new Mappers();
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8084/games")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post( Entity.json( mappers.toUnidentifiedVideojuego(videojuego) ) );
        switch ( response.getStatus() )
        {
            case 201: return response.readEntity( Videojuego.class );

            default:  ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public Videojuego requestUpdateVideojuego(Videojuego videojuego) throws VideojuegoException {

        Mappers mappers = new Mappers();
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8084/games/{id}")
                .resolveTemplate("id", videojuego.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put( Entity.json(mappers.toUnidentifiedVideojuego(videojuego)) );
        switch ( response.getStatus() )
        {
            case 204: return videojuego;

            case 404: throw new VideojuegoNotFoundException( videojuego.getId() );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public void requestDeleteVideojuego(String id) throws VideojuegoException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8084/games/{id}")
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
