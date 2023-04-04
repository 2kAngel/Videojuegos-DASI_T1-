package es.upsa.dasi.quarkus.common.providers;

import es.upsa.dasi.videojuegos.dtos.ErrorMessage;
import es.upsa.dasi.videojuegos.exceptions.EntityNotFoundException;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;

import javax.ws.rs.core.Response;

public class ExceptionMapperUtils {

    public static Response toResponse(VideojuegoException exception)
    {
        if ( exception  instanceof EntityNotFoundException enfException)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else
        {
            return Response.serverError()
                    .entity( new ErrorMessage( exception.getMessage() ))
                    .build();
        }
    }
}
