package es.upsa.dasi.videojuegos.plataformas.quarkus.services;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;

import java.util.List;

public interface Service {

    public Plataformas getPlataformasVideojuego(String id) throws VideojuegoException;

    public List<Plataformas> getPlataformas() throws VideojuegoException;

}
