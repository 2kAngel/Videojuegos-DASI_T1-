package es.upsa.dasi.videojuegosJakarta.controllers;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegosJakarta.beans.Action;
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
@Path("/forms")
public class FormsControllers
{
    @Inject
    Service service;

    @Inject
    Models models;

    @GET
    @Path("update/games/{id}")
    @UriRef("getUpdateVideojuegoForm")
    @Controller
    @View("/jsps/videojuego.jsp")
    public void getUpdateVideojuegoForm(@PathParam("id") String id) throws VideojuegoException
    {

        FullVideojuego videojuego = service.requestVideojuegoById(id);
        models.put("videojuego", videojuego);
        models.put("action", Action.UPDATE);
    }

    @GET
    @Path("delete/games/{id}")
    @UriRef("getDeleteVideojuegoForm")
    @Controller
    @View("/jsps/videojuego.jsp")
    public void getDeleteVideojuegoForm(@PathParam("id") String id) throws VideojuegoException
    {

        FullVideojuego videojuego = service.requestVideojuegoById(id);
        models.put("videojuego", videojuego);
        models.put("action", Action.DELETE);
    }

    @GET
    @Path("insert/games")
    @UriRef("getInsertVideojuegoForm")
    @Controller
    @View("/jsps/videojuego.jsp")
    public void getInsertVideojuegoForm()
    {

        models.put("action", Action.INSERT);
    }
}
