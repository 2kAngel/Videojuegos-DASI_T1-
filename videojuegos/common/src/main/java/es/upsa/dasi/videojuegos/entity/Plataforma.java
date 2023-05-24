package es.upsa.dasi.videojuegos.entity;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;

@Builder(setterPrefix = "with")
@With
public record Plataforma(String id, String id_videojuego, String id_desarrolladora, String nombre, LocalDate fecha_lanzamiento, String foto) {}
