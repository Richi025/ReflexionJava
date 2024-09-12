package edu.escuelaing.arep.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada que define un método para manejar solicitudes HTTP GET.
 * value: Ruta a la cual responde el método anotado.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMapping {
    public String value();
}
