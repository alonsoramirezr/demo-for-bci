# demo-for-bci
Uso exclusivo para evaluación de BCI

# Tools para ejecutar el proyecto
1. Intellij Idea (CE o UE)
2. Maven 3.8
3. Java 1.8 o superior
4. Postman

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
![diagrama de secuencia registro](https://user-images.githubusercontent.com/26873637/214446726-e9a3b538-5076-46aa-8fa1-97697a612d7d.png)

Autenticación
![diagrama autenticacion](https://user-images.githubusercontent.com/26873637/214446840-e70f9f84-d70e-4171-9859-d3b3aec14e6d.png)

Obtener usuarios
![diagrama get usuarios](https://user-images.githubusercontent.com/26873637/214446871-1bdc0caf-b858-4ead-84f6-528f720b060e.png)

Agregar usuarios
![diagrama agregar usuario](https://user-images.githubusercontent.com/26873637/214446882-2342366e-ba3c-4c2c-87d0-fb0d13679f6d.png)

Actualizar usuario
![diagrama update usuario](https://user-images.githubusercontent.com/26873637/214446894-33aa1a29-ec68-403b-a5fe-70cc7fe5e8e4.png)

# Diagrama entidad relación 
![diagrama entidad relacion](https://user-images.githubusercontent.com/26873637/214446926-9c290f59-22c6-43dc-a495-56bf26275577.png)


# Autorización de endpoint
Estos endpoints de tipo REST se encuentran protegidos por Spring Security, por lo que
para poder probar los endpoints de la ruta /api/v1/users debe registrase, obtener su JWT y
enviarlos por headers por postman de esta manera: 

![ejemplo autorizacion](https://user-images.githubusercontent.com/26873637/214446965-d07c0be4-c393-4620-b7dd-641ec908cd0f.png)


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
![Registro](https://user-images.githubusercontent.com/26873637/214447006-581a2bbf-d507-4996-aa18-f78fd4b25f64.png)

Intento de registro con email ya existente
![registro con email ya existente](https://user-images.githubusercontent.com/26873637/214447028-bade0cbd-a311-48cb-a5f6-469d011df965.png)

Intento de registro con password no valida
![intento de registro con password no valida](https://user-images.githubusercontent.com/26873637/214447040-8bcdaf23-b617-48e3-978c-c139c48fa81a.png)

Autenticación correcta
![autenticacion](https://user-images.githubusercontent.com/26873637/214447062-0bc94dba-0a90-44dc-8941-04fd5ceeea8d.png)

Autenticación no valida
![autenticacion no valida](https://user-images.githubusercontent.com/26873637/214447080-360a06a9-8ff8-4971-af91-ad562ae73c82.png)

Obtener usuarios
![get usuarios ok](https://user-images.githubusercontent.com/26873637/214447100-58a97b93-e720-4f0e-89e9-e0667d773a88.png)

Obtener usuarios sin JWT
![get usuarios unathorized](https://user-images.githubusercontent.com/26873637/214447107-2fe892cc-6382-40f6-982e-a91fca9a74a2.png)

Crear usuario
![crear usuario ok](https://user-images.githubusercontent.com/26873637/214447137-34fca636-0126-4dee-b114-db4295bd64d3.png)

Crear usuario sin JWT
![crear usuario no autorizado](https://user-images.githubusercontent.com/26873637/214447154-8b5737b0-6f52-48a3-ae35-61dcfbad59e9.png)

Crear usuario con email o password no validos
![crear usuario email y password no validos](https://user-images.githubusercontent.com/26873637/214447169-fd9f9df4-ca51-4580-b9fa-56c3fedb603c.png)

Crear usuario con email ya existente
![crear usuario con email ya existente](https://user-images.githubusercontent.com/26873637/214447195-1987b4de-713c-443a-b71e-7a3191a1c69d.png)

Actualizar usuario ok
![update ok](https://user-images.githubusercontent.com/26873637/214447218-2bf219a4-32fb-40f5-9a38-ffa80bdc9314.png)

Actualizar usuario sin JWT
![update sin token](https://user-images.githubusercontent.com/26873637/214447247-f7615137-9e49-4681-b52e-0b4149e9dc53.png)

Actualizar usuario no existente
![update usuario no existe](https://user-images.githubusercontent.com/26873637/214447263-17548209-10e7-4755-b310-7ed9d39f436e.png)

Actualizar usuario correo existente
![update correo existente](https://user-images.githubusercontent.com/26873637/214447276-2a60cd59-31ae-4f3b-9c33-e6cbd419a295.png)

Actualizar correo con email o password no validos
![update email o password no validos](https://user-images.githubusercontent.com/26873637/214447288-b1f315a8-df17-474d-ae0f-d3b0594aa6bd.png)

