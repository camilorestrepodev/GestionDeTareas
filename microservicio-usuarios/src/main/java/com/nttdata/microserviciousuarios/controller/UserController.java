package com.nttdata.microserviciousuarios.controller;

import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.model.Task;
import com.nttdata.microserviciousuarios.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene todos los usuarios de la aplicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Obtener un usuario por id", description = "Obtienes los usuarios creados por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Crear un usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @Operation(summary = "Actualiza un usuario", description = "Actualiza un usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @Operation(summary = "Elimina un usuario por id", description = "Elimina un usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener id de una tarea", description = "Obtener un id de una tarea creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/tasks/byuser/{userId}")
    public List<Task> getTaskById(@PathVariable Integer userId ){
        return userService.getByUserId(userId);
    }

    @Operation(summary = "Obtener tareas por rango de fechas de creación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Lista de tareas encontradas"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta - Rango de fechas inválido")
    })
    @GetMapping("/date-range")
    public List<Task> getTasksByCreationDateRange(
            @RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return userService.getTasksByCreationDateRange(startDate, endDate);
    }
}
