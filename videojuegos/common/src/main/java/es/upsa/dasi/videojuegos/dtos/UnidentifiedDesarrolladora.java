package es.upsa.dasi.videojuegos.dtos;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.Date;

@Builder(setterPrefix = "with")
@With
public record UnidentifiedDesarrolladora(String nombre, String nacionalidad, LocalDate fecha_creacion, String foto) {}
