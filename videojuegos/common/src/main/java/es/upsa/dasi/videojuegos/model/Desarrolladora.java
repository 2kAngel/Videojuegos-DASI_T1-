package es.upsa.dasi.videojuegos.model;

import lombok.Builder;
import lombok.With;

import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record Desarrolladora(
                            String id,
                            String nombre,
                            String nacionalidad,
                            Date fecha_creacion,
                            String foto
                            )
{ }
/*
*  ID VARCHAR(9),
    NOMBRE VARCHAR(100),
    NACIONALIDAD VARCHAR(100),
    FECHA_CREACION DATE,
    FOTO VARCHAR(200)
* */
