package es.upsa.dasi.videojuegos.model;

import lombok.Builder;
import lombok.With;

import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record Plataformas(String id,
                          String id_videojuego,
                          String id_desarrolladora,
                          String nombre,
                          Date fecha_lanzamiento,
                          String foto) {
}