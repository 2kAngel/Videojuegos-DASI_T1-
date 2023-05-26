package es.upsa.dasi.videojuegos.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.LocalDate;

@Data
@Builder(setterPrefix = "with")
@With
public class FullVideojuego {

    public FullVideojuego(){

    }

    public FullVideojuego(String id, String nombre, LocalDate fecha_lanzamiento, String genero, String cartel) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.genero = genero;
        this.cartel = cartel;
    }

    private String id;
    private String nombre;
    private LocalDate fecha_lanzamiento;
    private String genero;
    private String cartel;
}
