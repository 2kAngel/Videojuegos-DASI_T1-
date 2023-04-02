package es.upsa.dasi.repository.impl;

import es.upsa.dasi.daos.DatabaseDao;
import es.upsa.dasi.repository.Repository;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    DatabaseDao databaseDao;

    @Override
    public List<Videojuego> getVideojuegos() throws VideojuegoException {

        return databaseDao.selectVideojuegos();
    }

    @Override
    public Optional<Videojuego> getVideojuegoById(String id) throws VideojuegoException {

        return databaseDao.selectVideojuegoById(id);
    }
}
