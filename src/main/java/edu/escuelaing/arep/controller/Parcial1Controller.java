package edu.escuelaing.arep.controller;

import edu.escuelaing.arep.Annotations.GetMapping;
import edu.escuelaing.arep.Annotations.RequestMapping;
import edu.escuelaing.arep.Annotations.RequestParam;
import edu.escuelaing.arep.Annotations.RestController;
import edu.escuelaing.arep.service.Parcial1Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Parcial1Controller {

    private Parcial1Service service = new Parcial1Service();
    private Map<String, Method> routes = new HashMap<>();

    public Parcial1Controller() {
        // Mapea los métodos anotados con @GetMapping
        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                // Almacena la ruta completa incluyendo el prefijo /api
                String fullRoute = "/api" + getMapping.value();
                System.out.println("Registrando ruta: " + fullRoute);
                routes.put(fullRoute, method); // Guarda la ruta completa
            }
        }
    }

    public String routeRequest(String path, Map<String, String> queryParams) throws Exception {
        System.out.println("Solicitud recibida en la ruta: " + path);
        Method method = routes.get(path);  // Busca la ruta completa en el mapa de rutas

        // Verifica si existe un método mapeado para la ruta
        if (method != null) {
            Object[] params = new Object[method.getParameterCount()];
            for (int i = 0; i < method.getParameterCount(); i++) {
                if (method.getParameters()[i].isAnnotationPresent(RequestParam.class)) {
                    RequestParam requestParam = method.getParameters()[i].getAnnotation(RequestParam.class);
                    params[i] = queryParams.getOrDefault(requestParam.value(), requestParam.defaultValue());
                }
            }
            return (String) method.invoke(this, params);
        }
        return "404 Not Found";  // Si la ruta no se encuentra, devolver 404
    }

    @GetMapping("/calculatePower")
    public String calculatePower(@RequestParam(value = "base") String base, 
                                 @RequestParam(value = "exponente") String exponente) {
        // Realiza el cálculo de la potencia
        double baseVal = Double.parseDouble(base);
        double exponenteVal = Double.parseDouble(exponente);
        double resultado = service.calculatePower(baseVal, exponenteVal);
        return "El resultado de " + baseVal + " ^ " + exponenteVal + " es: " + resultado;
    }
}
