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

    @Override
    public void updateVideojuego(Videojuego videojuego) throws VideojuegoException {
        repository.updateVideojuego(videojuego);
    }

    @Override
    public void deleteVideojuegoById(String id) throws VideojuegoException {
        repository.deleteVideojuegoById(id);
    }

    @Override
    public Videojuego insertVideojuego(Videojuego videojuego) throws VideojuegoException {
        return repository.insertVideojuego(videojuego);
    }
}
