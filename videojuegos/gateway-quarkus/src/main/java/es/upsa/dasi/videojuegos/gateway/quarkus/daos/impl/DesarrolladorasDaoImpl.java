package es.upsa.dasi.videojuegos.gateway.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.dtos.ErrorMessage;
import es.upsa.dasi.videojuegos.exceptions.DesarrolladoraNotFoundException;
import es.upsa.dasi.videojuegos.exceptions.PlataformaNotFoundException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoNotFoundException;
import es.upsa.dasi.videojuegos.gateway.quarkus.daos.DesarrolladorasDao;
import es.upsa.dasi.videojuegos.model.Desarrolladora;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class DesarrolladorasDaoImpl implements DesarrolladorasDao
{

    @Override
    public List<Desarrolladora> requestDesarrolladoras() throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/developers")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        switch ( response.getStatus() )
        {
            case 200:   List<Desarrolladora> desarrolladoras = response.readEntity( new GenericType< List<Desarrolladora> >() {}  );
                return desarrolladoras;

            default:   ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public Desarrolladora requestDesarrolladoraById(String id) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                         .build()
                                         .target("http://localhost:8082/developers/{id}")
                                         .resolveTemplate("id", id)
                                         .request(MediaType.APPLICATION_JSON_TYPE)
                                         .get();

        switch ( response.getStatus() )
        {
            case 200:   Desarrolladora desarrolladora = response.readEntity( Desarrolladora.class );
                return desarrolladora;

            case 404:   throw new DesarrolladoraNotFoundException(id);

            default:    ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public Desarrolladora requestInsertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/developers")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post( Entity.json(desarrolladora) );
        switch ( response.getStatus() )
        {
            case 201: return response.readEntity( Desarrolladora.class );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public Desarrolladora requestUpdateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/developers/{id}")
                .resolveTemplate("id", desarrolladora.id())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put( Entity.json(desarrolladora) );
        switch ( response.getStatus() )
        {
            case 204: return desarrolladora;

            case 404: throw new DesarrolladoraNotFoundException( desarrolladora.id() );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }

    @Override
    public void requestDeleteDesarrolladora(String id) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/developers/{id}")
                .resolveTemplate("id", id)
                .request()
                .delete(  );
        switch ( response.getStatus() )
        {
            case 204: return ;

            case 404: throw new DesarrolladoraNotFoundException( id );

            default: ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }
}
