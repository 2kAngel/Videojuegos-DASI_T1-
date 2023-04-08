package es.upsa.dasi.videojuegos.plataformas.quarkus.repository;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;

public interface Repository {

    public Plataformas findPlataformasVideojuego(String id) throws VideojuegoException;
}
