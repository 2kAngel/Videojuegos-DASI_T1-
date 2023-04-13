package es.upsa.dasi.videojuegos.plataformas.quarkus.daos;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;

public interface DatabaseDao {

    Plataformas selectPlataformasVideojuegos(String id) throws VideojuegoException;

    public List<Plataformas> selectPlataformas() throws VideojuegoException;

}
