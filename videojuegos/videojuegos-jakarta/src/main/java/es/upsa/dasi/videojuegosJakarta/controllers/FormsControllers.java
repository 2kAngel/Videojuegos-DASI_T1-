package es.upsa.dasi.videojuegosJakarta.controllers;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegosJakarta.services.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.View;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RequestScoped
@Path("/{language}/forms")
public class FormsControllers
{
    @Inject
    Service service;

    @Inject
    Models models;



    @GET
    @Path("/insert/games")
    @UriRef("getFormInsertVideojuego")
    @Controller
    @View("/jsps/forms/videojuego.jsp")
    public void getFormInsertVideojuegoById()
    {

        models.put("action", Action.INSERT);
    }

    @GET
    @Path("/update/games/{id}")
    @UriRef("getFormUpdateVideojuegoById")
    @Controller
    @View("/jsps/forms/videojuego.jsp")
    public void getFormUpdateVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {
        FullVideojuego videojuego = service.requestVideojuegoById(id);
        models.put("videojuego", videojuego);
        models.put("action", Action.UPDATE);
    }

    @GET
    @Path("/delete/games/{id}")
    @UriRef("getFormDeleteVideojuegoById")
    @Controller
    @View("/jsps/forms/videojuego.jsp")
    public void getFormDeleteVideojuegoById(@PathParam("id") String id) throws VideojuegoException
    {

        models.put("action", Action.DELETE);
        models.put("videojuego", service.requestVideojuegoById(id));

    }


}
