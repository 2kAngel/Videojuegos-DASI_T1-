package es.upsa.dasi.videojuegos.model;

import lombok.Builder;
import lombok.With;

import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record Desarrolladora(String id,
                             String id_videojuego,
                             String nombre,
                             String nacionalidad,
                             Date fecha_creacion,
                             String foto) {
}
