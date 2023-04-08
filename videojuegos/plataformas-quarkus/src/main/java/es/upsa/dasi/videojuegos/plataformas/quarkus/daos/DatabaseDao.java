package es.upsa.dasi.videojuegos.plataformas.quarkus.daos;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;

public interface DatabaseDao {

    Plataformas selectPlataformasPelicula(String id) throws VideojuegoException;
}
