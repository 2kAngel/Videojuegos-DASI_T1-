package es.upsa.dasi.videojuegosJakarta.providers.converters;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements ParamConverter<LocalDate> {




    @Override
    public LocalDate fromString(String value)
    {
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString(LocalDate localDate)
    {
        return localDate.format( DateTimeFormatter.ISO_LOCAL_DATE );
    }
}
