package es.upsa.dasi.videojuegos.plataformas.quarkus.repository.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.plataformas.quarkus.daos.DatabaseDao;
import es.upsa.dasi.videojuegos.plataformas.quarkus.repository.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    DatabaseDao dao;
    @Override
    public Plataformas findPlataformasVideojuego(String id) throws VideojuegoException {

        return dao.selectPlataformasPelicula(id);
    }
}
