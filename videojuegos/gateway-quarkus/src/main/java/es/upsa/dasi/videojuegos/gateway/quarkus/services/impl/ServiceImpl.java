package es.upsa.dasi.videojuegos.gateway.quarkus.services.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.gateway.quarkus.repository.Repository;
import es.upsa.dasi.videojuegos.gateway.quarkus.services.Service;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ServiceImpl implements Service
{

    @Inject
    Repository repository;

    @Override
    public List<Videojuego> demandVideojuegos() throws VideojuegoException {
        return repository.findVideojuegos();
    }

    @Override
    public Videojuego demandVideojuegoById(String id) throws VideojuegoException {
        return repository.findVideojuegoById(id);
    }

    @Override
    public Videojuego demandInsertVideojuego(Videojuego videojuego) throws VideojuegoException {
        return repository.findInsertVideojuego(videojuego);
    }

    @Override
    public Videojuego demandUpdateVideojuego(Videojuego videojuego) throws VideojuegoException {
        return repository.findUpdateVideojuego(videojuego);
    }

    @Override
    public void demandDeleteVideojuego(String id) throws VideojuegoException {
            repository.findDeleteVideojuego(id);
    }
    //------------------------------------------------------------------------
    @Override
    public List<Desarrolladora> demandDesarrolladoras() throws VideojuegoException {
        return repository.findDesarrolladoras();
    }

    @Override
    public Desarrolladora demandDesarrolladoraById(String id) throws VideojuegoException {
        return repository.findDesarrolladoraById(id);
    }

    @Override
    public Desarrolladora demandInsertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {
        return repository.findInsertDesarrolladora(desarrolladora);
    }

    @Override
    public Desarrolladora demandUpdateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {
        return repository.findUpdateDesarrolladora(desarrolladora);
    }

    @Override
    public void demandDeleteDesarrolladora(String id) throws VideojuegoException {
        repository.findDeleteDesarrolladora(id);
    }
}
