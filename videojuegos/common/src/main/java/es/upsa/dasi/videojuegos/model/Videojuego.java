package es.upsa.dasi.videojuegos.model;

import lombok.*;

import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record Videojuego(String id,
                         String nombre,
                         Date fecha_lanzamiento,
                         String genero,
                         String cartel){

}
