package es.upsa.dasi.videojuegosJakarta.beans;

import jakarta.ws.rs.FormParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VideojuegoForm {

    @FormParam("id")
    private String id;

    @FormParam("nombre")
    private String nombre;

    @FormParam("fecha_lanzamiento")
    private String fecha_lanzamiento;

    @FormParam("genero")
    private String genero;

    @FormParam("cartel")
    private String cartel;
}
