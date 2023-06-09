package com.nttdata.microserviciotareas;

import com.nttdata.microserviciotareas.entity.Task;
import com.nttdata.microserviciotareas.exceptions.ApiRequestException;
import com.nttdata.microserviciotareas.repository.TaskRepository;
import com.nttdata.microserviciotareas.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    TaskRepository taskRepository;
    TaskService taskService;

    @BeforeEach
    void SetUp(){
        this.taskRepository = mock(TaskRepository.class);
        this.taskService = new TaskService(taskRepository);
    }

    @Test
    void testGetAllTasks() {
        List<Task> taskList = Arrays.asList(
                new Task("Cocinar", "Todos los dias", LocalDate.of(2023,10,20),2),
                new Task("Barrer", "Todos los dias", LocalDate.of(2023,10,20),2)
        );
        Mockito.when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> result = taskService.getAllTasks();
        Assertions.assertEquals(taskList, result);
    }

    @Test
    void testGetTaskById(){
        Long id = 1L;
        Task task = new Task("Barrer", "Todos los dias", LocalDate.of(2023,10,20),2);
        task.setId(id);

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        Task task1 = taskService.getTaskById(task.getId());

        Assertions.assertSame(task,task1);
    }

    @Test
    void testGetTaskById_NonExistingId_ThrowsException() {
        Task task = new Task("Cocinar", "Todos los dias", LocalDate.of(2023,10,20),2);
        ApiRequestException thrown = assertThrows(
                ApiRequestException.class,
                () -> this.taskService.getTaskById(task.getId()),
                "Tarea no encontrada con ID: " + task.getId()
        );
        Assertions.assertTrue(thrown.getMessage().contentEquals("Tarea no encontrada con ID: " + task.getId()));
    }

    @Test
    void testCreateUser() {
        Task task = new Task("Leer", "Todos los libros", LocalDate.of(2023,10,20),3);

        when(taskRepository.save(any(Task.class))).thenReturn(task);
        taskRepository.save(task);
        Task taskNew = taskService.createTask(task);

        Assertions.assertSame(taskNew, task);
    }

    @Test
    void testCreateTask_DescriptionAndDateNull_ThrowsException() {
        Task task = new Task("Leer",null,null,2);
        ApiRequestException thrown = assertThrows(
                ApiRequestException.class,
                () -> this.taskService.createTask(task),
                "La descripción y la fecha de vencimiento son obligatorias"
        );
        Assertions.assertTrue(thrown.getMessage().contentEquals("La descripción y la fecha de vencimiento son obligatorias"));
    }

    @Test
    void testUpdateUser() {
        Task task = new Task("Leer", "Todos los libros", LocalDate.of(2023,10,20),3);

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        Task taskUpdate = new Task();
        taskUpdate.setId(task.getId());
        taskUpdate.setTitle("Jugar videojuegos");
        taskUpdate.setDescription("Probar todos los videojuegos");
        taskUpdate.setUserId(4);

        Task task1 = taskService.updateTask(taskUpdate.getId(), taskUpdate);

        verify(taskRepository, times(1)).findById(task.getId());
    }

    @Test
    void testDeleteUser() {
        Long id = 1L;
        Task task = new Task("Leer", "Todos los libros", LocalDate.of(2023,10,20),3);
        doReturn(Optional.of(task)).when(taskRepository).findById(id);
        taskService.deleteTask(id);
        verify(taskRepository, times(1)).findById(id);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testGetTasksByCreationDateRange() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        List<Task> expectedTasks = Arrays.asList(
                new Task("Tarea 1", "Descripción 1", LocalDate.of(2022, 3, 15),2),
                new Task("Tarea 2", "Descripción 2", LocalDate.of(2022, 6, 30),3)
        );

        when(taskRepository.findByCreationDateRange(startDate, endDate)).thenReturn(expectedTasks);

        List<Task> actualTasks = taskService.getTasksByCreationDateRange(startDate, endDate);

        Assertions.assertEquals(expectedTasks.size(), actualTasks.size());
        Assertions.assertEquals(expectedTasks.get(0).getTitle(), actualTasks.get(0).getTitle());
        Assertions.assertEquals(expectedTasks.get(1).getDescription(), actualTasks.get(1).getDescription());
    }
}
