package es.upsa.dasi.videojuegos.dtos;

import java.time.LocalDate;
import java.util.Date;

public record UnidentifiedDesarrolladora(String nombre, String nacionalidad, LocalDate fecha_creacion, String foto) {}
