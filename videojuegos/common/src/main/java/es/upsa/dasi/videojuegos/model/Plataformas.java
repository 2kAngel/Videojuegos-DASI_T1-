package es.upsa.dasi.videojuegos.model;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record Plataformas(String id,
                          String id_videojuego,
                          String id_desarrolladora,
                          String nombre,
                          LocalDate fecha_lanzamiento,
                          String foto) {
}
