package es.upsa.dasi.videojuegos.gateway.quarkus.repository.impl;

import es.upsa.dasi.videojuegos.exceptions.VideojuegoException;
import es.upsa.dasi.videojuegos.gateway.quarkus.daos.DesarrolladorasDao;
import es.upsa.dasi.videojuegos.gateway.quarkus.daos.PlataformasDao;
import es.upsa.dasi.videojuegos.gateway.quarkus.daos.VideojuegosDao;
import es.upsa.dasi.videojuegos.gateway.quarkus.repository.Repository;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Plataformas;
import es.upsa.dasi.videojuegos.model.Videojuego;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{

    @Inject
    VideojuegosDao videojuegosDao;

    @Inject
    DesarrolladorasDao desarrolladorasDao;

    @Inject
    PlataformasDao plataformasDao;

    @Override
    public List<Videojuego> findVideojuegos() throws VideojuegoException {
        return videojuegosDao.requestVideojuegos();
    }

    @Override
    public Videojuego findVideojuegoById(String id) throws VideojuegoException {
        return videojuegosDao.requestVideojuegoById(id);
    }

    @Override
    public Videojuego findInsertVideojuego(Videojuego videojuego) throws VideojuegoException {
        return videojuegosDao.requestInsertVideojuego(videojuego);
    }

    @Override
    public Videojuego findUpdateVideojuego(Videojuego videojuego) throws VideojuegoException {
        return videojuegosDao.requestUpdateVideojuego(videojuego);
    }

    @Override
    public void findDeleteVideojuego(String id) throws VideojuegoException {
        videojuegosDao.requestDeleteVideojuego(id);
    }

    //--------------------------------------------------------

    @Override
    public List<Desarrolladora> findDesarrolladoras() throws VideojuegoException {
        return desarrolladorasDao.requestDesarrolladoras();
    }

    @Override
    public Desarrolladora findDesarrolladoraById(String id) throws VideojuegoException {
        return desarrolladorasDao.requestDesarrolladoraById(id);
    }

    @Override
    public Desarrolladora findInsertDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {
        return desarrolladorasDao.requestInsertDesarrolladora(desarrolladora);
    }

    @Override
    public Desarrolladora findUpdateDesarrolladora(Desarrolladora desarrolladora) throws VideojuegoException {
        return desarrolladorasDao.requestUpdateDesarrolladora(desarrolladora);
    }

    @Override
    public void findDeleteDesarrolladora(String id) throws VideojuegoException {
        desarrolladorasDao.requestDeleteDesarrolladora(id);
    }

    @Override
    public Optional<Plataformas> findPlataformaVideojuego(String id) throws VideojuegoException {
       return plataformasDao.requestPlataformaVideojuego(id);
    }

    //--------------------------------------------------------


}
