package es.upsa.dasi.quarkus.common.providers;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoRuntimeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VideojuegoRuntimeExceptionMapper implements ExceptionMapper<VideojuegoRuntimeException> {


    @Override
    public Response toResponse(VideojuegoRuntimeException exception) {

        VideojuegoException cause = (VideojuegoException) exception.getCause();
        return ExceptionMapperUtils.toResponse( cause );
    }
}
