package es.upsa.dasi.videojuegos.gateway.quarkus.daos.impl;

import es.upsa.dasi.videojuegos.dtos.ErrorMessage;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.gateway.quarkus.daos.PlataformasDao;
import es.upsa.dasi.videojuegos.model.Plataformas;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class PlataformasDaoImpl implements PlataformasDao
{

    @Override
    public Optional<Plataformas> requestPlataformaVideojuego(String id) throws VideojuegoException {
        Response response = ClientBuilder.newBuilder()
                                    .build()
                                    .target("http://localhost:8083/games/{id}/plataformas")
                                    .resolveTemplate("id", id)
                                    .request(MediaType.APPLICATION_JSON_TYPE)
                                    .get();

        switch ( response.getStatus() )
        {
            case 200 : return Optional.of( response.readEntity( Plataformas.class ) );

            case 404:  return Optional.empty();

            default:   ErrorMessage errorMessage = response.readEntity( ErrorMessage.class );
                throw new VideojuegoException( errorMessage.message() );
        }
    }
}
