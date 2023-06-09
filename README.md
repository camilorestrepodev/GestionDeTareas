# Aplicación web orientada a microservicios REST (NTT DATA)
Esta aplicación web implementa dos microservicios: uno para la gestión de usuarios y otro para la gestión de tareas. Cada microservicio tiene su propia rama en el repositorio de Git, lo que permite un desarrollo y mantenimiento independiente de cada uno. Además, se incluye documentación para ambos microservicios, con dos URLs diferentes para acceder a cada uno de ellos.
Arquitectura

La arquitectura de esta aplicación es una arquitectura de microservicios. En esta arquitectura, los diferentes componentes de la aplicación se dividen en servicios independientes, en este caso, el microservicio de Usuarios y el microservicio de Tareas.
Cada microservicio se desarrolla y se despliega de forma independiente, lo que permite un desarrollo y mantenimiento más modular y escalable. Los microservicios se comunican entre sí a través de la integración de OpenFeign, que facilita la comunicación entre los diferentes servicios de manera eficiente.
Además, cada microservicio tiene su propia base de datos, en este caso, MySQL, lo que permite un acoplamiento bajo y un aislamiento de datos entre los microservicios.

<div align="center" > <img alt="logo" height="200" width="800" align="center" src="https://github.com/camilorestrepodev/GestionDeTareas/assets/115324147/255da26d-c980-465f-b138-f127b88ade19">
</div>
 
## Requisitos:
Asegúrate de tener los siguientes requisitos antes de ejecutar la aplicación:

•	Java versión 17 o superior

•	Gestor de dependencias: Gradle

•	Base de datos MySQL

•	OpenFeign (Spring Cloud)

## Configuración
### Microservicios de usuarios
Antes de comenzar, asegúrese de tener una base de datos configurada y actualice las credenciales de la base de datos en el archivo **application.properties**.
```java
spring.datasource.url= jdbc:mysql://localhost:3306/microserviciousuarios?useSSL=false&serverTimezone=UTC&useLegacyDateTimeCode=false
server.port=8001
spring.application.name=user-service
spring.datasource.username= root
spring.datasource.password= 050509
spring.jpa.show-sql= true
spring.jpa.hibernate.ddl-auto= update
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL8Dialect
```
### Microservicios de tareas
```java
spring.datasource.url= jdbc:mysql://localhost:3306/microserviciotareas?useSSL=false&serverTimezone=UTC&useLegacyDateTimeCode=false
server.port=8003
spring.application.name=task-service
spring.datasource.username= root
spring.datasource.password= 050509
spring.jpa.show-sql= true
spring.jpa.hibernate.ddl-auto= update
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL8Dialect
```

# Microservicio de Usuarios
## Funcionalidades
El microservicio de Usuarios proporciona las siguientes funcionalidades:

•	Crear un nuevo usuario.

•	Obtener información de un usuario específico.

•	Actualizar los datos de un usuario.

•	Eliminar un usuario.

## Endpoints de la API

La API del microservicio de Usuarios expone los siguientes endpoints:

•	POST /users - Crea un nuevo usuario.

•	GET /users/{id} - Obtiene información de un usuario específico.

•	PUT /users/{id} - Actualiza los datos de un usuario.

•	DELETE /users/{id} - Elimina un usuario.

•	GET /users – Obtiene todos los usuarios

•	GET /users/tasks/byuser/{userId} – Obtiene id de una tarea

•	GET /users/date-range – Obtiene tareas por rango de fecha de creación


## Documentación con Swagger
La documentación de la API del microservicio de Usuarios se encuentra disponible a través de Swagger en la siguiente URL: http://localhost:8001/swagger-ui.html.

# Microservicio de Tareas
## Funcionalidades
El microservicio de Tareas proporciona las siguientes funcionalidades:

•	Crear una nueva tarea asignada a un usuario.

•	Obtener información de una tarea específica.

•	Actualizar los datos de una tarea.

•	Eliminar una tarea.

## Endpoints de la API
La API del microservicio de Tareas expone los siguientes endpoints:

•	POST /tasks - Crea una nueva tarea asignada a un usuario.

•	GET /tasks/{id} - Obtiene información de una tarea específica.

•	PUT /tasks/{id} - Actualiza los datos de una tarea.

•	DELETE /tasks/{id} - Elimina una tarea.

•	GET /users/tasks/byuser/{userId} – Obtiene id de una tarea

•	GET /users/date-range – Obtiene tareas por rango de fecha de creación



## Documentación con Swagger
La documentación de la API del microservicio de Tareas se encuentra disponible a través de Swagger en la siguiente URL: http://localhost:8003/swagger-ui.html.

## Pruebas unitarias
Se han incluido pruebas unitarias para asegurar el correcto funcionamiento de los controladores y servicios de usuarios y tareas. Puedes encontrar estas pruebas en el directorio src/test del proyecto.

## Manejo de errores
La aplicación implementa un manejo de errores centralizado para capturar y manejar diferentes tipos de excepciones. Esto garantiza una respuesta coherente y adecuada en caso de errores. Además, se han implementado filtros para validar y proteger las APIs.
