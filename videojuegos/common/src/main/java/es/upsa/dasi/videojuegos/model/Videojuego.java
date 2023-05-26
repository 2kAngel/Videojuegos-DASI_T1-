package es.upsa.dasi.videojuegos.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder(setterPrefix = "with")
@With
public class Videojuego{

    private String id;
    private String nombre;
    private String genero;
    private String cartel;
    private LocalDate fecha_lanzamiento;

    public Videojuego(){

    }

    public Videojuego(String id, String nombre, String genero, String cartel, LocalDate fecha_lanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.cartel = cartel;
        this.fecha_lanzamiento = fecha_lanzamiento;
    }
}
