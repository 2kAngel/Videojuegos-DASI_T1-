package es.upsa.dasi.videojuegosJakarta.beans;

import es.upsa.dasi.videojuegos.model.Videojuego;
import es.upsa.dasi.videojuegos.model.Videojuego;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@RequestScoped
@Named("data")
public class Data
{
    private List<Videojuego> videojuegos;

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }
}