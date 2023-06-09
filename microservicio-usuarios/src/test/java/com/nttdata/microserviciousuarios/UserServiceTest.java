package com.nttdata.microserviciousuarios;

import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.exceptions.ApiRequestException;
import com.nttdata.microserviciousuarios.feignclients.TaskFeignClient;
import com.nttdata.microserviciousuarios.model.Task;
import com.nttdata.microserviciousuarios.repository.UserRepository;
import com.nttdata.microserviciousuarios.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {
    UserRepository userRepository;
    UserService userService;
    TaskFeignClient taskFeignClient;

    @BeforeEach
    void SetUp() {
        this.userRepository = mock(UserRepository.class);
        this.taskFeignClient = mock(TaskFeignClient.class);
        this.userService = new UserService(userRepository, taskFeignClient);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "John"),
                new User(2L, "Jane")
        );
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        Assertions.assertEquals(users, result);
    }

    @Test
    void testGetUserById(){
        User user = new User(1L,"Camilo");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User userId = userService.getUserById(user.getId());

        Assertions.assertSame(user,userId);
    }

    @Test
    void testGetUserById_NonExistingId_ThrowsException() {
        User user = new User(1L,"Camilo");
        ApiRequestException thrown = assertThrows(
                ApiRequestException.class,
                () -> this.userService.getUserById(user.getId()),
                "Usuario no encontrado con ID: " + user.getId()
        );
        Assertions.assertTrue(thrown.getMessage().contentEquals("Usuario no encontrado con ID: " + user.getId()));
    }

    @Test
    void testCreateUser() {
        User user = new User(1L,"Camilo");

        when(userRepository.save(any(User.class))).thenReturn(user);
        userRepository.save(user);
        User userNew = userService.createUser(user);

        Assertions.assertSame(userNew, user);
    }

    @Test
    void testUpdateUser() {
        User user = new User(1L, "Camilo");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User userUpdate = new User();
        userUpdate.setId(user.getId());
        userUpdate.setName("Andres");

        User user1 = userService.updateUser(userUpdate.getId(), userUpdate);

        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void testDeleteUser() {
        Long id = 1L;
        User user = new User(id, "John Doe");
        doReturn(Optional.of(user)).when(userRepository).findById(id);
        userService.deleteUser(id);
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testGetByUserId() {
        Integer userId = 1;
        List<Task> expectedTasks = Arrays.asList(new Task("Cocinar","Papas a la francesa", LocalDate.of(2023,10,20),2),
                new Task("Barrer","Todos los dias", LocalDate.of(2023,10,20),3));
        when(taskFeignClient.getByUserId(userId)).thenReturn(expectedTasks);
        UserService userService = new UserService(userRepository,taskFeignClient);
        List<Task> actualTasks = userService.getByUserId(userId);
        Assertions.assertEquals(expectedTasks, actualTasks);
        verify(taskFeignClient, times(1)).getByUserId(userId);
    }

    @Test
    void testGetTasksByCreationDateRange() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        List<Task> expectedTasks = Arrays.asList(
                new Task("Tarea 1", "Descripción 1", LocalDate.of(2022, 3, 15),2),
                new Task("Tarea 2", "Descripción 2", LocalDate.of(2022, 6, 30),3)
        );

        when(taskFeignClient.getTaskByCreationDateRange(startDate, endDate)).thenReturn(expectedTasks);

        List<Task> actualTasks = userService.getTasksByCreationDateRange(startDate, endDate);

        Assertions.assertEquals(expectedTasks.size(), actualTasks.size());
        Assertions.assertEquals(expectedTasks.get(0).getTitle(), actualTasks.get(0).getTitle());
        Assertions.assertEquals(expectedTasks.get(1).getDescription(), actualTasks.get(1).getDescription());
    }

}

