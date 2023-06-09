package es.upsa.dasi.videojuegosJakarta.services.impl;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegosJakarta.repository.Repository;
import es.upsa.dasi.videojuegosJakarta.services.Service;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServiceImpl implements Service {

    @Inject
    Repository repository;

    @Override
    public List<Videojuego> requestVideojuegos() throws VideojuegoException {

        return repository.requestVideojuegos();
    }

    @Override
    public FullVideojuego requestVideojuegoById(String id) throws VideojuegoException {

        return repository.requestVideojuegoById(id);
    }

    @Override
    public Videojuego requestInsertVideojuego(Videojuego videojuego) throws VideojuegoException {

        return repository.requestInsertVideojuego(videojuego);
    }

    @Override
    public Videojuego requestUpdateVideojuego(Videojuego videojuego) throws VideojuegoException {

        return repository.requestUpdateVideojuego(videojuego);
    }

    @Override
    public void requestDeleteVideojuego(String id) throws VideojuegoException {

        repository.requestDeleteVideojuegoById(id);
    }
}
