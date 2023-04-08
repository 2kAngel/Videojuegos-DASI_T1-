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

    @Override
    public void replaceDesarrolladoraById(Desarrolladora desarrolladora) throws VideojuegoException {

        databaseDao.updateDesarrolladora(desarrolladora);
    }

    @Override
    public void removeDesarrolladoraById(String id) throws VideojuegoException {

        databaseDao.deleteDesarrolladoraById(id);
    }

    @Override
    public Desarrolladora addDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {

        return databaseDao.insertDesarrolladora(desarrolladora);
    }
}
