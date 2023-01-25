# demo-for-bci
Uso exclusivo para evaluación de BCI

# Tools para ejecutar el proyecto
1. Intellij Idea (CE o UE)
2. Maven 3.8
3. Java 1.8 o superior
4. Postman

# Colección de Postman
La colección de postman se encuentra dentro del proyecto en una carpeta llamada postman.
Esta colección puede ser importada a su postman de manera local y poder probar de esa manera.


# Tecnologias utilizadas para el desarrollo del proyecto
1. Springboot 2.7.9-SNAPSHOT
2. Java 11
3. Base de datos en memoria H2
4. JPA
5. JWT
6. Lombok
7. Junit
8. Spring security

# Como probar el proyecto de manera local
1. Descargue el repositorio
2. Una vez descargado, entre a intellij y seleccione File -> New... -> Project from existing sources
3. Ahora seleccione el repositorio descargado y selecciona Ok
4. Luego selecciona 'Import project from external model' y selecciona la opción maven
5. Ya importado el proyecto a nuestro IDE, haremos click en el boton Maven que se encuentra 
al costado derecho del IDE. 
6. Ahí, seleccionaremos la opción de flecha hacia abajo y haremos download sources 
(o tambien ejecutar el comando mvn clean install desde un terminal)
7. Una vez descargada las dependencias, haremos un refresh de maven 
haciendo click en Maven y luego haremos click refresh. 
8. Ahora tiene dos opciones, ir a la clase Main (DemoForBciApplication.java) y hacer click en la flecha verde
o ejecutar el comando DemoForBciApplication mvn spring-boot:run
9. Ahora, si todo salio bien, puede ejecutar los endpoints. 

# Evaluación Java
Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de
error.
Todos los mensajes deben seguir el formato:

```{"mensaje": "mensaje de error"}```

# Registro de usuarios
Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña",
más un listado de objetos "teléfono", respetando el siguiente formato:

```
{
    "name": "nombre",
    "email": "email@dominio.org",
    "password": "password",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

# Diagrama de secuencias

Los siguientes diagramas de secuencia representan como se comporta el proyecto y el flujo entre clases a alto nivel

Registro de usuario
![diagrama de secuencia registro.png](..%2F..%2Fdiagrama%20de%20secuencia%20registro.png)

Autenticación
![diagrama autenticacion.png](..%2F..%2Fdiagrama%20autenticacion.png)

Obtener usuarios
![diagrama get usuarios.png](..%2F..%2Fdiagrama%20get%20usuarios.png)

Agregar usuarios
![diagrama agregar usuario.png](..%2F..%2Fdiagrama%20agregar%20usuario.png)

Actualizar usuario
![diagrama update usuario.png](..%2F..%2Fdiagrama%20update%20usuario.png)

# Diagrama entidad relación 

![diagrama entidad relacion.png](..%2F..%2Fdiagrama%20entidad%20relacion.png)

# Autorización de endpoint
Estos endpoints de tipo REST se encuentran protegidos por Spring Security, por lo que
para poder probar los endpoints de la ruta /api/v1/users debe registrase, obtener su JWT y
enviarlos por headers por postman de esta manera: 

![ejemplo autorizacion.png](..%2F..%2Fejemplo%20autorizacion.png)

El valor KEY debe ser "Authorization" y el valor VALUE debe ser de la siguiente forma:
Bearer + token obtenido por registro de usuario o autenticación

En este caso, los dos endpoints que no necesitan de JWT para realizar peticiones son
localhost:8080/api/v1/auth/register y localhost:8080/api/v1/auth/authenticate, por lo que debe empezar el flujo
desde el endpoint de register para poder crear un usuario.

El body del request para registrarse es el siguiente:

```
{
    "name": "nombre",
    "email": "email@dominio.org",
    "password": "password",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
Mientras que el body del request para autenticarse es:

```
{
    "email": "email@dominio.org",
    "password": "password",
}
```
Estos endpoints no requieren de JWT.

# Endpoints de usuarios
Como se mencionaba en el punto anterior, estos endpoints se encuentran protegidos por
Spring security, por lo que es necesario de un JWT para poder ser invocados.

Estos endpoints son:
1. localhost:8080/api/v1/users (GET) - Obtiene todos los usuarios de la BD
2. localhost:8080/api/v1/users (POST) - Agrega un usuario a la BD
3. localhost:8080/api/v1/users/<id_usuario> (PUT) - modifica un usuario

El body del request para agregar un usuario (POST) es el siguiente: 

```
{
    "name": "nombre",
    "email": "email@dominio.org",
    "password": "password",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
Mientras que el body del request para actualizar un usuario es:

```
{
    "name": "nombre",
    "email": "email@dominio.org",
    "password": "password",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
    "active":true
}
```
Cabe mencionar que este endpoint espera el UUID del 
usuario en la URL para poder ejecutarse correctamente

# Evidencias de las ejecuciones
Registro de usuario
![Registro.png](..%2F..%2FRegistro.png)

Intento de registro con email ya existente
![registro con email ya existente.png](..%2F..%2Fregistro%20con%20email%20ya%20existente.png)![intento de registro con password no valida.png](..%2F..%2Fintento%20de%20registro%20con%20password%20no%20valida.png)

Intento de registro con password no valida
![intento de registro con password no valida.png](..%2F..%2Fintento%20de%20registro%20con%20password%20no%20valida.png)

Autenticación correcta
![autenticacion.png](..%2F..%2Fautenticacion.png)

Autenticación no valida
![autenticacion no valida.png](..%2F..%2Fautenticacion%20no%20valida.png)

Obtener usuarios
![get usuarios ok.png](..%2F..%2Fget%20usuarios%20ok.png)

Obtener usuarios sin JWT
![get usuarios unathorized.png](..%2F..%2Fget%20usuarios%20unathorized.png)

Crear usuario
![crear usuario ok.png](..%2F..%2Fcrear%20usuario%20ok.png)

Crear usuario sin JWT
![crear usuario no autorizado.png](..%2F..%2Fcrear%20usuario%20no%20autorizado.png)

Crear usuario con email o password no validos
![crear usuario email y password no validos.png](..%2F..%2Fcrear%20usuario%20email%20y%20password%20no%20validos.png)

Crear usuario con email ya existente
![crear usuario con email ya existente.png](..%2F..%2Fcrear%20usuario%20con%20email%20ya%20existente.png)

Actualizar usuario ok
![update ok.png](..%2F..%2Fupdate%20ok.png)

Actualizar usuario sin JWT
![update sin token.png](..%2F..%2Fupdate%20sin%20token.png)

Actualizar usuario no existente
![update usuario no existe.png](..%2F..%2Fupdate%20usuario%20no%20existe.png)

Actualizar usuario correo existente
![update correo existente.png](..%2F..%2Fupdate%20correo%20existente.png)

Actualizar correo con email o password no validos
![update email o password no validos.png](..%2F..%2Fupdate%20email%20o%20password%20no%20validos.png)

# Colección de Postman

En esta sección del readme queda adjunta la colección de postman utilizada para probar el proyecto

[bci demo.postman_collection.json](..%2F..%2Fbci%20demo.postman_collection.json)