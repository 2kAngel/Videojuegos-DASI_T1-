package es.upsa.dasi.videojuegosJakarta.controllers.forms;

import es.upsa.dasi.videojuegosJakarta.constraintValidation.LocalDateMin;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
public class VideojuegoForm {



    @FormParam("nombre")
    @MvcBinding
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100, message = "{error.size}")
    private String nombre;

    @FormParam("fecha_lanzamiento")
    @MvcBinding
    @NotNull
    @LocalDateMin(value = "1950-01-01", inclusive = true)//fecha minima = fecha de salida del primer videojuego aprox
    private LocalDate fecha_lanzamiento;

    @FormParam("genero")
    @MvcBinding
    @NotNull
    @NotBlank
    @Size(min = 2, max = 30, message = "{error.size}")
    private String genero;

    @FormParam("cartel")
    @MvcBinding
    @NotNull
    @NotBlank
    @Pattern(regexp = "(http|https)://[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?", message = "{error.url}")
    private String cartel;
}
