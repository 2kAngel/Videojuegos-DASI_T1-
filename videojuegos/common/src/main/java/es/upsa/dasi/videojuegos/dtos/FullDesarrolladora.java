package es.upsa.dasi.videojuegos.dtos;

import lombok.Builder;
import lombok.With;

import java.util.Date;
@Builder(setterPrefix = "with")
@With
public record FullDesarrolladora(String id,
                                 String nombre,
                                 String nacionalidad,
                                 Date fecha_creacion,
                                 String foto
                                ) { }
