package es.upsa.dasi.videojuegosJakarta.constraintValidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LocalDateConstraintMinValidator.class)
@Target(value = {ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateMin {

    String value();
    boolean inclusive() default false;
    String message() default "{es.upsa.dasi.videojuegosJakarta.constraintValidation.LocalDateMin.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
