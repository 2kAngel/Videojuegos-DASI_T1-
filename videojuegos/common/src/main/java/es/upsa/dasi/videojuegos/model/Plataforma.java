package es.upsa.dasi.videojuegos.model;


import lombok.Builder;
import lombok.With;

import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record Plataforma(
        String id,
        String nombre,
        Date fecha,
        String foto
) { }

/* ID VARCHAR(9),
    NOMBRE VARCHAR(100),
    FECHA_LANZAMIENTO DATE,
    FOTO VARCHAR(200), */