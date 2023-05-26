package es.upsa.dasi.videojuegosJakarta.providers;

import es.upsa.dasi.videojuegosJakarta.providers.converters.LocalDateConverter;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Provider
public class LocalDateParamConverterProvider implements ParamConverterProvider {

    private LocalDateConverter localDateConverter = new LocalDateConverter();

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {

        if ( LocalDate.class.isAssignableFrom(aClass) )
        {
            return (ParamConverter<T>) localDateConverter;
        }
        return null;
    }
}
