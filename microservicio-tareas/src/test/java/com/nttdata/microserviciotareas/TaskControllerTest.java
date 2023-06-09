package com.nttdata.microserviciotareas;

import com.nttdata.microserviciotareas.controller.TaskController;
import com.nttdata.microserviciotareas.entity.Task;
import com.nttdata.microserviciotareas.repository.TaskRepository;
import com.nttdata.microserviciotareas.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskControllerTest {

    @Mock
    private TaskRepository taskRepository;
    private TaskService taskService;
    private TaskController taskController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.taskRepository = mock(TaskRepository.class);
        this.taskService = new TaskService(taskRepository);
        this.taskController = new TaskController(taskService);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        // Arrange
        Task task1 = new Task("Tarea 1", "Descripción 1", LocalDate.of(2023, 8, 10), 1);
        Task task2 = new Task("Tarea 2", "Descripción 2", LocalDate.of(2023, 8, 10), 1);
        List<Task> expectedTasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(expectedTasks);

        // Act & Assert
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Tarea 1"))
                .andExpect(jsonPath("$[0].description").value("Descripción 1"))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[1].title").value("Tarea 2"))
                .andExpect(jsonPath("$[1].description").value("Descripción 2"))
                .andExpect(jsonPath("$[1].userId").value(1));
    }


}
