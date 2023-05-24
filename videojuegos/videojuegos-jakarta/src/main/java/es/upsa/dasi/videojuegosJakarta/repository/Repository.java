package es.upsa.dasi.videojuegosJakarta.repository;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;

public interface Repository {

    public List<Videojuego> requestVideojuegos() throws VideojuegoException;
    public FullVideojuego requestVideojuegoById(String id) throws VideojuegoException;
    public Videojuego requestInsertVideojuego(Videojuego videojuego) throws VideojuegoException;
    public Videojuego requestUpdateVideojuego(Videojuego videojuego) throws VideojuegoException;
    public void requestDeleteVideojuego(String id) throws VideojuegoException;
}
