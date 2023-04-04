package es.upsa.dasi.videojuegos.dtos;

import java.util.Date;

public record UnidentifiedPlataforma(String id, String id_videojuego, String nombre, Date fecha_lanzamiento, String foto) {}
