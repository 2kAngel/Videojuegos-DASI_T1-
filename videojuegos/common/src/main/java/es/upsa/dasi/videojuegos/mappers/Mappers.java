package es.upsa.dasi.videojuegos.mappers;

import es.upsa.dasi.videojuegos.dtos.UnidentifiedDesarrolladora;
import es.upsa.dasi.videojuegos.dtos.UnidentifiedVideojuego;
import es.upsa.dasi.videojuegos.model.Desarrolladora;
import es.upsa.dasi.videojuegos.model.Videojuego;

public class Mappers {

    public Videojuego toVideojuego(UnidentifiedVideojuego unidentifiedVideojuego)
    {
        return Videojuego.builder()
                .withNombre(unidentifiedVideojuego.nombre() )
                .withFecha_lanzamiento(unidentifiedVideojuego.fecha_lanzamiento() )
                .withGenero(unidentifiedVideojuego.genero() )
                .withCartel(unidentifiedVideojuego.cartel() )
                .build();
    }

    public UnidentifiedVideojuego toUnidentifiedVideojuego(Videojuego videojuego)
    {
        return UnidentifiedVideojuego.builder()
                .withNombre(videojuego.nombre() )
                .withFecha_lanzamiento(videojuego.fecha_lanzamiento() )
                .withGenero(videojuego.genero() )
                .withCartel(videojuego.cartel() )
                .build();
    }

    public Desarrolladora toDesarrolladora(UnidentifiedDesarrolladora unidentifiedDesarrolladora)
    {
        return Desarrolladora.builder()
                .withNombre(unidentifiedDesarrolladora.nombre())
                .withNacionalidad(unidentifiedDesarrolladora.nacionalidad())
                .withFecha_creacion(unidentifiedDesarrolladora.fecha_creacion())
                .withFoto(unidentifiedDesarrolladora.foto())
                .build();
    }
}
