package es.upsa.dasi.videojuegos.gateway.quarkus.daos;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;

import java.util.Optional;

public interface PlataformasDao
{
    public Optional<Plataformas> requestPlataformaVideojuego(String id) throws VideojuegoException;
}
