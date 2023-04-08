package es.upsa.dasi.videojuegos.gateway.quarkus.services;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;

import java.util.List;
import java.util.Optional;

public interface Service
{
    public List<Videojuego> demandVideojuegos() throws VideojuegoException;
    public Videojuego demandVideojuegoById(String id) throws VideojuegoException;
    public Videojuego demandInsertVideojuego(Videojuego videojuego) throws VideojuegoException;
    public Videojuego demandUpdateVideojuego(Videojuego videojuego) throws VideojuegoException;
    public void demandDeleteVideojuego(String id) throws VideojuegoException;

    //-----------------------------------------------

    public List<Desarrolladora> demandDesarrolladoras() throws VideojuegoException;
    public Desarrolladora demandDesarrolladoraById(String id) throws VideojuegoException;
    public Desarrolladora demandInsertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public Desarrolladora demandUpdateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public void demandDeleteDesarrolladora(String id) throws VideojuegoException;
    //-----------------------------------------------

    public Optional<Plataformas> demandPlataformaVideojuego(String id) throws VideojuegoException;

}
