package es.upsa.dasi.videojuegos.dtos;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record UnidentifiedVideojuego(String nombre,
                                     LocalDate fecha_lanzamiento,
                                     String genero,
                                     String cartel) {}
