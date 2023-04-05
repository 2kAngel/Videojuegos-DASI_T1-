package es.upsa.dasi.videojuegos.gateway.quarkus.daos;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;

import java.util.List;

public interface DesarrolladorasDao
{

    public List<Desarrolladora> requestDesarrolladoras() throws VideojuegoException;
    public Desarrolladora requestDesarrolladoraById(String id) throws VideojuegoException;
    public Desarrolladora requestInsertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public Desarrolladora requestUpdateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException;
    public void requestDeleteDesarrolladora(String id) throws VideojuegoException;

}
