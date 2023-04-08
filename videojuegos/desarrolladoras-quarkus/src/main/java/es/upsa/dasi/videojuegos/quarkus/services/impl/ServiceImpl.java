package es.upsa.dasi.videojuegos.quarkus.services.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.quarkus.services.Service;
import es.upsa.dasi.videojuegos.quarkus.repository.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceImpl implements Service {

    @Inject
    Repository repository;

    @Override
    public List<Desarrolladora> findDesarrolladoras() throws VideojuegoException {

        return repository.getDesarrolladoras();
    }

    @Override
    public Optional<Desarrolladora> findDesarrolladoraById(String id) throws VideojuegoException {

        return repository.getDesarrolladoraById(id);
    }

    @Override
    public void updateDesarrolladoraById(Desarrolladora desarrolladora) throws VideojuegoException {

        repository.replaceDesarrolladoraById(desarrolladora);
    }

    @Override
    public void deleteDesarrolladoraById(String id) throws VideojuegoException {

        repository.removeDesarrolladoraById(id);
    }

    @Override
    public Desarrolladora createDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {

        return repository.addDesarrolladora(desarrolladora);
    }
}
