package es.upsa.dasi.videojuegos.entity;

import java.time.LocalDate;
import java.util.Date;

public record Plataforma(String id, String id_videojuego, String id_desarrolladora, String nombre, LocalDate fecha_lanzamiento, String foto) {}
