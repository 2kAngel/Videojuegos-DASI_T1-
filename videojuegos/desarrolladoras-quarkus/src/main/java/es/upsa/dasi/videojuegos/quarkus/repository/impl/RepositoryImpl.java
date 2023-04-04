package es.upsa.dasi.videojuegos.quarkus.repository.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.quarkus.repository.Repository;
import es.upsa.dasi.videojuegos.quarkus.daos.DatabaseDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    DatabaseDao databaseDao;

    @Override
    public List<Desarrolladora> getDesarrolladoras() throws VideojuegoException {

        return databaseDao.selectDesarrolladoras();
    }

    @Override
    public Optional<Desarrolladora> getDesarrolladoraById(String id) throws VideojuegoException {

        return databaseDao.selectDesarrolladoraById(id);
    }
}
