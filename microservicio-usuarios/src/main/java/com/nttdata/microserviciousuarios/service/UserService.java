package com.nttdata.microserviciousuarios.service;

import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.exceptions.ApiRequestException;
import com.nttdata.microserviciousuarios.feignclients.TaskFeignClient;
import com.nttdata.microserviciousuarios.model.Task;
import com.nttdata.microserviciousuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskFeignClient taskFeignClient;

    public UserService(UserRepository userRepository, TaskFeignClient taskFeignClient) {
        this.userRepository = userRepository;
        this.taskFeignClient = taskFeignClient;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Usuario no encontrado con ID: " + id));
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Usuario no encontrado con ID: " + id));
        user.setName(updatedUser.getName());
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Usuario no encontrado con ID: " + id));
        userRepository.delete(user);
    }
    public List<Task> getByUserId(Integer userId){
        return taskFeignClient.getByUserId(userId);
    }

    public List<Task> getTasksByCreationDateRange(LocalDate startDate, LocalDate endDate) {
        return taskFeignClient.getTaskByCreationDateRange(startDate, endDate);
    }
}
