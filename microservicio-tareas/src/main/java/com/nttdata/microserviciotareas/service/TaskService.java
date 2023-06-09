package com.nttdata.microserviciotareas.service;

import com.nttdata.microserviciotareas.entity.Task;
import com.nttdata.microserviciotareas.exceptions.ApiRequestException;
import com.nttdata.microserviciotareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Tarea no encontrada con ID: " + id));
    }

    public Task createTask(Task task) {
        if (task.getDescription() == null || task.getCreationDate() == null) {
            throw new ApiRequestException("La descripción y la fecha de vencimiento son obligatorias");
        }
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Tarea no encontrada con ID: " + id));
        task.setDescription(updatedTask.getDescription());
        task.setCreationDate(updatedTask.getCreationDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Tarea no encontrada con ID: " + id));
        taskRepository.delete(task);
    }

    public List<Task> findByUserId(Integer userId){
        return taskRepository.findByUserId(userId);
    }
}
