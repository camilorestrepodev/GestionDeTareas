package com.nttdata.microserviciousuarios.service;

import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.exceptions.ResourceNotFoundException;
import com.nttdata.microserviciousuarios.feignclients.TaskFeignClient;
import com.nttdata.microserviciousuarios.model.Task;
import com.nttdata.microserviciousuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    TaskFeignClient taskFeignClient;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        user.setName(updatedUser.getName());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        userRepository.delete(user);
    }

    public List<Task> getByUserId(Integer userId){
        return taskFeignClient.getByUserId(userId);
    }

}
