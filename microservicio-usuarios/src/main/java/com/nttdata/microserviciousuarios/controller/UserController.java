package com.nttdata.microserviciousuarios.controller;

import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.model.Task;
import com.nttdata.microserviciousuarios.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene todos los usuarios de la aplicación")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Obtener un usuario por id", description = "Obtienes los usuarios creados por id")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Crear un usuario", description = "Crea un nuevo usuario en el sistema")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @Operation(summary = "Actualiza un usuario", description = "Actualiza un usuario por id")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @Operation(summary = "Elimina un usuario por id", description = "Elimina un usuario en el sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/byuser/{userId}")
    public List<Task> getTaskById(@PathVariable Integer userId ){
        return userService.getByUserId(userId);
    }

}
