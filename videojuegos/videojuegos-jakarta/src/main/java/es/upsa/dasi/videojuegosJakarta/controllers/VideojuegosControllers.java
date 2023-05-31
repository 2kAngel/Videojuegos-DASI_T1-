package es.upsa.dasi.videojuegosJakarta.controllers;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegosJakarta.controllers.forms.VideojuegoForm;
import es.upsa.dasi.videojuegosJakarta.services.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestScoped
@Path("/{language}/games")
public class VideojuegosControllers {

    @PathParam("language")
    String language;

    @Inject
    Service service;

    @Inject
    Models models;

    @Inject
    MvcContext mvc;

    @Inject
    Validator validator;

    @Inject
    BindingResult bindingResult;

    @GET
    @Controller
    @UriRef("getVideojuegos")
    @View("/jsps/videojuegos.jsp")
    public void getVideojuegos() throws VideojuegoException {

        models.put("videojuegos", service.requestVideojuegos());
    }

    @GET
    @Path("{id}")
    @Controller
    @UriRef("getVideojuegoById")
    @View("/jsps/forms/videojuego.jsp")
    public void getVideojuegoById(@PathParam("id") String id) throws VideojuegoException {

        models.put("action", Action.SELECT);
        models.put("videojuego", service.requestVideojuegoById(id));
    }

    @POST
    @Controller
    @UriRef("postVideojuego")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postVideojuego(@BeanParam VideojuegoForm form) throws VideojuegoException {

        Videojuego videojuego = Videojuego.builder()
                .withNombre(form.getNombre() )
                .withFecha_lanzamiento(form.getFecha_lanzamiento())
                .withGenero(form.getGenero() )
                .withCartel(form.getCartel() )
                .build();

        Set< ConstraintViolation<VideojuegoForm> > constraintViolations = validator.validate(form);
        if ( constraintViolations.isEmpty() )
        {
            Videojuego insertedVideojuego = service.requestInsertVideojuego(videojuego);
            return Response.seeOther(mvc.uri("getVideojuegoById", Map.of("id", insertedVideojuego.getId(), "language", language)))
                    .build();
        }

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<VideojuegoForm> constraintViolation : constraintViolations)
        {
            String message = constraintViolation.getMessage();
            String property = constraintViolation.getPropertyPath().toString();
            errors.put(property, message);
        }

        models.put("errors", errors);
        models.put("videojuego", form);
        models.put("action", Action.INSERT);
        return Response.ok()
                .entity("/jsps/forms/videojuego.jsp")
                .build();
    }

    @PUT
    @Path("{id}")
    @Controller
    @UriRef("putVideojuegoById")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response putVideojuegoById(@PathParam("id") String id, @BeanParam @Valid VideojuegoForm form) throws VideojuegoException {

        Videojuego videojuego = Videojuego.builder()
                .withId(id)
                .withNombre(form.getNombre() )
                .withFecha_lanzamiento(form.getFecha_lanzamiento())
                .withGenero(form.getGenero() )
                .withCartel(form.getCartel() )
                .build();

        if ( !bindingResult.isFailed() )
        {
            service.requestUpdateVideojuego(videojuego);
            return Response.seeOther(mvc.uri("getVideojuegoById", Map.of("id", id, "language", language)))
                    .build();
        }

        Map<String, String> errors = new HashMap<>();
        for (ParamError paramError : bindingResult.getAllErrors())
        {
            String message = paramError.getMessage();
            String property = paramError.getParamName();
            errors.put(property, message);
        }

        models.put("errors", errors);
        models.put("videojuego", videojuego);
        models.put("action", Action.UPDATE);
        return Response.ok()
                .entity("/jsps/forms/videojuego.jsp")
                .build();
    }

    @DELETE
    @Path("{id}")
    @Controller
    @UriRef("deleteVideojuegoById")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteVideojuegoById(@PathParam("id") String id) throws VideojuegoException {

        service.requestDeleteVideojuego(id);
        return Response.seeOther(  mvc.uri("getVideojuegos", Map.of("language", language))  )
                .build();
    }
}
