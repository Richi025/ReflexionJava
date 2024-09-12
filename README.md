# Proyecto: Servidor Web Concurrente con Cálculo de Potencias

Este proyecto implementa un servidor HTTP concurrente en Java que sirve archivos estáticos desde un directorio específico y ofrece un servicio REST para calcular la potencia de dos números. Además, maneja múltiples solicitudes de clientes simultáneamente usando un pool de hilos de tamaño fijo.



## Descripción del Servicio

El servidor proporciona un servicio REST que permite a los usuarios calcular la potencia de un número (base) elevado a otro número (exponente) mediante una solicitud HTTP. Además, el servidor implementa un caché que almacena los cálculos anteriores para evitar recalcular la misma operación si ya se ha solicitado antes. El cliente puede acceder a este servicio desde un archivo `index.html` donde puede ingresar los números, o directamente mediante una solicitud HTTP a la API del servidor.

## Comenzando

Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local para fines de desarrollo y pruebas.

### Requisitos previos

Necesitas instalar las siguientes herramientas y configurar sus dependencias:

1. **Java** (versiones 7 u 8)
    ```sh
    java -version
    ```

    Debes obtener una respuesta como esta:
    ```sh
    java version "1.8.0"
    Java(TM) SE Runtime Environment (build 1.8.0-b132)
    Java HotSpot(TM) 64-Bit Server VM (build 25.0-b70, mixed mode)
    ```

2. **Maven**
    - Descarga Maven desde [aquí](http://maven.apache.org/download.html)
    - Sigue las instrucciones de instalación [aquí](http://maven.apache.org/download.html#Installation)

    Verifica la instalación:
    ```sh
    mvn -version
    ```

    Debes obtener una respuesta como esta:
    ```sh
    Apache Maven 3.2.5 (12a6b3acb947671f09b81f49094c53f426d8cea1; 2014-12-14T12:29:23-05:00)
    Maven home: /Users/tu_usuario/Applications/apache-maven-3.2.5
    Java version: 1.8.0, vendor: Oracle Corporation
    Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home/jre
    ```

3. **Git**
    - Instala Git siguiendo las instrucciones [aquí](http://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

    Verifica la instalación:
    ```sh
    git --version
    ```

    Debes obtener una respuesta como esta:
    ```sh
    git version 2.2.1
    ```

### Instalación

1. Clona el repositorio y navega al directorio del proyecto:
    ```sh
    git clone https://github.com/tu_usuario/servidor_web_concurrente.git
    cd servidor_web_concurrente
    ```

2. Compila el proyecto:
    ```sh
    mvn package
    ```

    Deberías ver una salida similar a esta:
    ```sh
    [INFO] --- jar:3.3.0:jar (default-jar) @ servidor_web_concurrente ---
    [INFO] Building jar: C:\Users\tu_usuario\servidor_web_concurrente\target\servidor_web_concurrente-1.0-SNAPSHOT.jar
    [INFO] BUILD SUCCESS
    ```

3. Ejecuta la aplicación:
    ```sh
    java -cp target/servidor_web_concurrente-1.0-SNAPSHOT.jar edu.escuelaing.arep.Parcial1Application
    ```

    Al ejecutar la aplicación, deberías ver el siguiente mensaje:

    ```
    Ready to receive on port 35000...
    ```

    Ahora puedes acceder a la página `index.html` y otros recursos estáticos para interactuar con el servicio.

## Arquitectura

### Descripción General

El servidor concurrente simple está diseñado para manejar múltiples solicitudes HTTP de clientes simultáneamente usando un pool de hilos. El servidor es capaz de servir archivos estáticos y procesar solicitudes REST para calcular la potencia de dos números.

### Componentes

#### 1. **Parcial1Application**
   - **Función**: Es la clase principal del servidor. Inicializa el `ServerSocket` en un puerto específico y escucha las conexiones entrantes de los clientes. Utiliza un pool de hilos fijo (`ExecutorService`) para manejar cada solicitud de cliente concurrentemente.
   - **Responsabilidades**:
     - Aceptar conexiones de clientes entrantes.
     - Delegar el manejo de cada conexión a un controlador de solicitudes (`Parcial1Controller`).
     - Manejar el ciclo de vida del servidor.

#### 2. **Parcial1Controller**
   - **Función**: Esta clase es responsable de procesar las solicitudes individuales de los clientes. Usa reflexión para enrutar las solicitudes a los métodos correctos que manejan los diferentes servicios.
   - **Responsabilidades**:
     - Leer y analizar las solicitudes HTTP.
     - Determinar qué método debe manejar la solicitud y ejecutarlo.
     - Devolver las respuestas HTTP adecuadas al cliente.

#### 3. **Parcial1Service**
   - **Función**: Clase encargada de la lógica del negocio, en este caso, de calcular la potencia de dos números. También maneja un caché para evitar recalcular solicitudes repetidas.
   - **Responsabilidades**:
     - Calcular y devolver la potencia de dos números.
     - Manejar y almacenar resultados previamente calculados.

### Flujo de interacción

1. **Inicialización del servidor**: `Parcial1Application` inicia y configura el `ServerSocket` en el puerto 35000. También prepara un pool de hilos para manejar las solicitudes de los clientes.

2. **Manejo de solicitudes**:
   - Cuando un cliente envía una solicitud HTTP, el servidor acepta la conexión y crea una nueva instancia de `Parcial1Controller`.
   - `Parcial1Controller` analiza la solicitud y la enruta al método correspondiente para manejarla.
   - Se devuelve una respuesta HTTP al cliente con el resultado solicitado o un archivo estático.

3. **Concurrencia**: Las solicitudes de múltiples clientes se manejan de manera concurrente, utilizando el pool de hilos del servidor, lo que permite al servidor procesar varias conexiones al mismo tiempo.

## Ejemplo de uso

### Interfaz Web

1. Accede a la interfaz web en tu navegador a través de `http://localhost:35000/`. Aquí podrás ingresar dos números: la base y el exponente.
2. Presiona el botón "Calcular" y verás el resultado de la potencia calculada en la pantalla.

### Solicitud REST

También puedes acceder al servicio directamente mediante una solicitud HTTP como esta:
```
http://localhost:35000/api/calculatePower?base=2&exponente=3
```

El servidor calculará el resultado y devolverá algo como:

```
El resultado de 2 ^ 3 es: 8.0
```



## Construido con

* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Git](http://git-scm.com/) - Sistema de control de versiones

## Autores

* **[Tu nombre]**

## Licencia

Este proyecto está licenciado bajo la GNU


## Comandos necesarios 
Iniciar proyecto maven
```
mvn archetype:generate -DgroupId=edu.escuelaing.arep -DartifactId=reflexion -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

```

Generar javadoc
```
mvn javadoc:javadoc -DreportOutputDirectory=C:\Users\alexa\Concurrencia\javadoc
```

Crear imagen en docker
```
docker build --tag nombre_imagen .
```

Levantar el docker compose
```
docker-compose up -d
```

Referencia de la imagen en el repositorio
```
docker tag dockersparkprimer dnielben/firstsprkwebapprepo
docker login
docker push dnielben/firstsprkwebapprepo:latest
```