package es.upsa.dasi.videojuegos.quarkus.services;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;

import java.util.List;
import java.util.Optional;

public interface Service {

    public List<Desarrolladora> findDesarrolladoras() throws VideojuegoException;
    public Optional<Desarrolladora> findDesarrolladoraById(String id) throws VideojuegoException;
    public void updateDesarrolladoraById(Desarrolladora desarrolladora) throws VideojuegoException;
    public void deleteDesarrolladoraById(String id) throws VideojuegoException;
    Desarrolladora createDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
}
