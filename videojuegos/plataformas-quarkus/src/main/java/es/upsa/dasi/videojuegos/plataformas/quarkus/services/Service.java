package es.upsa.dasi.videojuegos.plataformas.quarkus.services;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;

public interface Service {

    public Plataformas getPlataformasVideojuego(String id) throws VideojuegoException;
}
