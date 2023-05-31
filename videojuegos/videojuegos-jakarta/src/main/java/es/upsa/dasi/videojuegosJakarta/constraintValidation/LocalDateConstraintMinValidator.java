package es.upsa.dasi.videojuegosJakarta.constraintValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConstraintMinValidator implements ConstraintValidator<LocalDateMin, LocalDate> {

    private LocalDate localDateMin;
    private boolean inclusive;

    @Override
    public void initialize(LocalDateMin constraintAnnotation) {

        String value = constraintAnnotation.value();
        inclusive = constraintAnnotation.inclusive();
        localDateMin = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {

        if(localDate == null) return true;
        return (inclusive == false) ? localDate.compareTo(localDateMin) > 0 : localDate.compareTo(localDateMin) >= 0;
    }
}
