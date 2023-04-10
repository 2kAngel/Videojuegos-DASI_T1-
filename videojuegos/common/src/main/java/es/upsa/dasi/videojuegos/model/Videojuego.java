package es.upsa.dasi.videojuegos.model;

import lombok.*;

import java.time.LocalDate;

@Builder(setterPrefix = "with")
@With
public record Videojuego(String id,
                         String nombre,
                         LocalDate fecha_lanzamiento,
                         String genero,
                         String cartel){

}
