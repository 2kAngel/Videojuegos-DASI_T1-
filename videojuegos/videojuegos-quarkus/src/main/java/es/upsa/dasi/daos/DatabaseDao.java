package es.upsa.dasi.daos;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;
import java.util.Optional;

public interface DatabaseDao {

    public List<Videojuego> selectVideojuegos() throws VideojuegoException;

    public Optional<Videojuego> selectVideojuegoById(String id) throws VideojuegoException;
}
