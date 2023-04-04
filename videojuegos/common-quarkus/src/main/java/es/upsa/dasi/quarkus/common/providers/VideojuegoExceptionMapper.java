package es.upsa.dasi.quarkus.common.providers;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VideojuegoExceptionMapper implements ExceptionMapper<VideojuegoException> {


    @Override
    public Response toResponse(VideojuegoException exception) {

        return ExceptionMapperUtils.toResponse( exception );
    }
}
