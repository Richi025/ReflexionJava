package edu.escuelaing.arep;

import edu.escuelaing.arep.controller.Parcial1Controller;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parcial1Application {

    public static void main(String[] args) throws Exception {
        Parcial1Controller controller = new Parcial1Controller();
        ServerSocket serverSocket = new ServerSocket(35000);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream();

                String inputLine = in.readLine();
                if (inputLine != null && !inputLine.isEmpty()) {
                    System.out.println("Solicitud recibida: " + inputLine);
                    String[] requestParts = inputLine.split(" ");
                    String path = requestParts[1].split("\\?")[0];

                    if (path.equals("/")) {
                        // Servir el archivo HTML index.html desde src/main/resources/static
                        serveStaticResource(out, "index.html");
                    } else {
                        // Manejar otras solicitudes (como calcular potencia)
                        Map<String, String> queryParams = getQueryParams(requestParts[1]);
                        String response = controller.routeRequest(path, queryParams);
                        out.write(("HTTP/1.1 200 OK\r\n" +
                                   "Content-Type: text/html\r\n" +
                                   "\r\n" +
                                   response).getBytes());
                    }
                }
            }
        }
    }

    /**
     * Sirve un archivo estático desde el directorio resources/static.
     * @param out El OutputStream para enviar la respuesta.
     * @param resource El nombre del archivo a servir.
     */
    private static void serveStaticResource(OutputStream out, String resource) throws IOException {
        // Usa el ClassLoader para obtener el recurso dentro de src/main/resources/static
        InputStream fileStream = Parcial1Application.class.getClassLoader().getResourceAsStream("static/" + resource);

        if (fileStream != null) {
            byte[] fileContent = fileStream.readAllBytes();
            out.write(("HTTP/1.1 200 OK\r\n" +
                       "Content-Type: text/html\r\n" +
                       "\r\n").getBytes());
            out.write(fileContent);
        } else {
            // Si el archivo no se encuentra
            out.write(("HTTP/1.1 404 Not Found\r\n" +
                       "Content-Type: text/html\r\n" +
                       "\r\n" +
                       "<h1>404 Not Found</h1>").getBytes());
        }
    }

    private static Map<String, String> getQueryParams(String request) {
        Map<String, String> queryParams = new HashMap<>();
        if (request.contains("?")) {
            String[] pairs = request.split("\\?")[1].split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                queryParams.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : "");
            }
        }
        return queryParams;
    }
}
