package es.upsa.dasi.repository;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;
import java.util.Optional;

public interface Repository {

    public List<Videojuego> getVideojuegos() throws VideojuegoException;
    public Optional<Videojuego> getVideojuegoById(String id) throws VideojuegoException;

    public void updateVideojuego(Videojuego videojuego) throws VideojuegoException;
    public void deleteVideojuegoById(String id) throws VideojuegoException;

    public Videojuego insertVideojuego(Videojuego videojuego) throws VideojuegoException;
}
