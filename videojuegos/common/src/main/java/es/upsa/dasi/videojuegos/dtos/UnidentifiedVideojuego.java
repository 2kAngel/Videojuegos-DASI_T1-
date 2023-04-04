package es.upsa.dasi.videojuegos.dtos;

import lombok.Builder;
import lombok.With;

import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record UnidentifiedVideojuego(String id, String nombre, Date fecha_lanzamiento, String genero, String cartel) {}
