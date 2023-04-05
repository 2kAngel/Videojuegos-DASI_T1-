package es.upsa.dasi.videojuegos.gateway.quarkus.daos;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;

public interface VideojuegosDao
{

    public List<Videojuego> requestVideojuegos() throws VideojuegoException;
    public Videojuego requestVideojuegoById(String id) throws VideojuegoException;
    public Videojuego requestInsertVideojuego(Videojuego videojuego) throws VideojuegoException;
    public Videojuego requestUpdateVideojuego(Videojuego videojuego) throws VideojuegoException;
    public void requestDeleteVideojuego(String id) throws VideojuegoException;


}
