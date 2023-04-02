package es.upsa.dasi.services;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;
import java.util.Optional;

public interface Service {

    public List<Videojuego> findVideojuegos() throws VideojuegoException;
    public Optional<Videojuego> findVideojuegoById(String id) throws VideojuegoException;
}
