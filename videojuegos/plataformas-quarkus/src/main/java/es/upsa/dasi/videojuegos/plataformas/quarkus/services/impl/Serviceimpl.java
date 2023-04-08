package es.upsa.dasi.videojuegos.plataformas.quarkus.services.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.plataformas.quarkus.repository.Repository;
import es.upsa.dasi.videojuegos.plataformas.quarkus.services.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Serviceimpl implements Service {

    @Inject
    Repository repository;
    @Override
    public Plataformas getPlataformasVideojuego(String id) throws VideojuegoException {

        return repository.findPlataformasVideojuego(id);
    }
}
