package es.upsa.dasi.videojuegosJakarta.controllers;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegosJakarta.beans.Action;
import es.upsa.dasi.videojuegosJakarta.beans.Data;
import es.upsa.dasi.videojuegosJakarta.beans.VideojuegoForm;
import es.upsa.dasi.videojuegosJakarta.services.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.MvcContext;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@RequestScoped
@Path("/{languaje}/games")
public class VideojuegosControllers {

    @PathParam("languaje")
    String languaje;

    @Inject
    Service service;

    @Inject
    Data data;

    @Inject
    Models models;

    @Inject
    MvcContext mvc;

    @Inject
    BindingResult bindingResult;

    @GET
    @UriRef("getVideojuegos")
    @Controller
    public Response getVideojuegos() throws VideojuegoException
    {

        List<Videojuego> videojuegos = service.requestVideojuegos();
        models.put("videojuegos", videojuegos);
        data.setVideojuegos( videojuegos );

        return Response.ok()
                .entity( "/jsps/videojuegos.jsp" )
                .build();
    }

    @GET
    @Path("{id}")
    @UriRef("getVideojuegoById")
    @Controller
    public Response getVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {

        FullVideojuego fullVideojuego = service.requestVideojuegoById(id);
        models.put("videojuego", fullVideojuego);
        models.put("action", Action.QUERY);

        return Response.ok()
                .entity("/jsps/videojuego.jsp")
                .build();
    }

    @POST
    @UriRef("insertVideojuego")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Controller
    public Response insertVideojuego(@BeanParam VideojuegoForm form) throws VideojuegoException
    {
        Videojuego videojuegoDTO = Videojuego.builder()
                .withNombre(form.getNombre() )
                .withFecha_lanzamiento(LocalDate.parse(form.getFecha_lanzamiento()))
                .withGenero(form.getGenero() )
                .withCartel(form.getCartel() )
                .build();
        Videojuego videojuego = service.requestInsertVideojuego( videojuegoDTO );

        return Response.seeOther( mvc.uri("getVideojuegos") )
                .build();
    }

    @PUT
    @Path("{id}")
    @UriRef("updateVideojuegoById")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Controller
    public Response updateVideojuegoById(@PathParam("id") String id, @BeanParam VideojuegoForm form) throws VideojuegoException
    {
        Videojuego videojuegoDTO = Videojuego.builder()
                .withId( id )
                .withNombre(form.getNombre() )
                .withFecha_lanzamiento(LocalDate.parse(form.getFecha_lanzamiento()))
                .withGenero(form.getGenero() )
                .withCartel(form.getCartel() )
                .build();
        Videojuego videojuego = service.requestUpdateVideojuego( videojuegoDTO );

        return Response.seeOther( mvc.uri("getVideojuegos") )
                .build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UriRef("deleteVideojuegoById")
    @Controller
    public Response deleteVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {
        service.requestDeleteVideojuego( id );

        return Response.seeOther( mvc.uri("getVideojuegos") )
                .build();
    }
}
