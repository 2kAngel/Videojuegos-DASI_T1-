package es.upsa.dasi.videojuegos.quarkus.repository;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;

import java.util.List;
import java.util.Optional;

public interface Repository {

    public List<Desarrolladora> getDesarrolladoras() throws VideojuegoException;
    public Optional<Desarrolladora> getDesarrolladoraById(String id) throws VideojuegoException;
}
