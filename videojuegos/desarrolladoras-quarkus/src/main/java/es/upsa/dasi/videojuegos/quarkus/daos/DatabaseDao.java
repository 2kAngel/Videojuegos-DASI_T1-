package es.upsa.dasi.videojuegos.quarkus.daos;


import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;

import java.util.List;
import java.util.Optional;

public interface DatabaseDao {

    public List<Desarrolladora> selectDesarrolladoras() throws VideojuegoException;
    public Optional<Desarrolladora> selectDesarrolladoraById(String id) throws VideojuegoException;
    public void updateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public void deleteDesarrolladoraById(String id) throws VideojuegoException;

    public Desarrolladora insertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
}
