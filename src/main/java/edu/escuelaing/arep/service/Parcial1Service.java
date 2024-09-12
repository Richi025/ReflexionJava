package edu.escuelaing.arep.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio que contiene la lógica para calcular la potencia de dos números y manejar un cache.
 */
public class Parcial1Service {

    // Cache para almacenar los resultados de las solicitudes previas
    private Map<String, Double> cache = new HashMap<>();

    /**
     * Calcula la potencia de base^exponente o la recupera del cache si ya fue calculada.
     * @param base La base de la potencia.
     * @param exponente El exponente de la potencia.
     * @return El resultado de base^exponente.
     */
    public double calculatePower(double base, double exponente) {
        // Crea una clave única para identificar la solicitud
        String key = base + "^" + exponente;
        
        // Verifica si el resultado ya está en el cache
        if (cache.containsKey(key)) {
            System.out.println("Resultado encontrado en cache: " + key);
            return cache.get(key);  // Devuelve el resultado almacenado en el cache
        } else {
            // Calcula el resultado si no está en el cache
            double resultado = Math.pow(base, exponente);
            // Almacena el resultado en el cache
            cache.put(key, resultado);
            System.out.println("Resultado calculado y almacenado en cache: " + key);
            return resultado;
        }
    }
}
