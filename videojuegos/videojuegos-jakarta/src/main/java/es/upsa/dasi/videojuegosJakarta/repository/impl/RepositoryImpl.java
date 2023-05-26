package es.upsa.dasi.videojuegosJakarta.repository.impl;

import es.upsa.dasi.videojuegos.dtos.FullVideojuego;
import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegosJakarta.daos.Gatewaydao;
import es.upsa.dasi.videojuegosJakarta.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RepositoryImpl implements Repository {


    @Inject
    Gatewaydao gatewayDao;
    @Override
    public List<Videojuego> requestVideojuegos() throws VideojuegoException {
        return gatewayDao.requestVideojuegos();
    }

    @Override
    public FullVideojuego requestVideojuegoById(String id) throws VideojuegoException {
        return gatewayDao.requestVideojuegoById(id);
    }

    @Override
    public Videojuego requestInsertVideojuego(Videojuego videojuego) throws VideojuegoException {

        return gatewayDao.requestInsertVideojuego(videojuego);
    }

    @Override
    public Videojuego requestUpdateVideojuego(Videojuego videojuego) throws VideojuegoException {

        return gatewayDao.requestUpdateVideojuego(videojuego);
    }

    @Override
    public void requestDeleteVideojuegoById(String id) throws VideojuegoException {
        gatewayDao.requestDeleteVideojuego(id);
    }
}
