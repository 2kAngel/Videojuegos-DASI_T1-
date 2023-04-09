package es.upsa.dasi.videojuegos.plataformas.quarkus.repository;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;

import java.util.List;

public interface Repository {

    public Plataformas findPlataformasVideojuego(String id) throws VideojuegoException;

    public List<Plataformas> findPlataformas() throws VideojuegoException;

}
