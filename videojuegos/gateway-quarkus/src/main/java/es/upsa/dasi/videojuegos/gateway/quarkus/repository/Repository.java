package es.upsa.dasi.videojuegos.gateway.quarkus.repository;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    public List<Videojuego> findVideojuegos() throws VideojuegoException;
    public Videojuego findVideojuegoById(String id) throws VideojuegoException;
    public Videojuego findInsertVideojuego(Videojuego videojuego) throws VideojuegoException;
    public Videojuego findUpdateVideojuego(Videojuego videojuego) throws VideojuegoException;
    public void findDeleteVideojuego(String id) throws VideojuegoException;

    //-----------------------------------------------

    public List<Desarrolladora> findDesarrolladoras() throws VideojuegoException;
    public Desarrolladora findDesarrolladoraById(String id) throws VideojuegoException;
    public Desarrolladora findInsertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public Desarrolladora findUpdateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public void findDeleteDesarrolladora(String id) throws VideojuegoException;

    //-----------------------------------------------

    public Optional<Plataformas> findPlataformaVideojuego(String id) throws VideojuegoException;

}
