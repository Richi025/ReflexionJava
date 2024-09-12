package edu.escuelaing.arep.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para marcar parámetros de un método como provenientes
 * de los parámetros de una solicitud HTTP (query params).
 * value: Nombre del parámetro de la solicitud.
 * defaultValue: Valor por defecto si el parámetro no es proporcionado.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    String value();
    String defaultValue() default "";
}
