package es.upsa.dasi.services.impl;

import es.upsa.dasi.repository.Repository;
import es.upsa.dasi.services.Service;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceImpl implements Service {

    @Inject
    Repository repository;

    @Override
    public List<Videojuego> findVideojuegos() throws VideojuegoException
    {
        return repository.getVideojuegos();
    }

    @Override
    public Optional<Videojuego> findVideojuegoById(String id) throws VideojuegoException {

        return repository.getVideojuegoById(id);
    }
}
