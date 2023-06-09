package com.nttdata.microserviciotareas;

import com.nttdata.microserviciotareas.entity.Task;
import com.nttdata.microserviciotareas.repository.TaskRepository;
import com.nttdata.microserviciotareas.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class TaskServiceTest {
    TaskRepository taskRepository;
    TaskService taskService;

    @BeforeEach
    void SetUp(){
        this.taskRepository = mock(TaskRepository.class);
        this.taskService = new TaskService(taskRepository);
    }

    @Test
    void createTask(){
        Task task = new Task();
    }
}
