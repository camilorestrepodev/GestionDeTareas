package com.nttdata.microserviciousuarios;

import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.repository.UserRepository;
import com.nttdata.microserviciousuarios.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {
    UserRepository userRepository;
    UserService userService;

    @BeforeEach
    void SetUp() {
        this.userRepository = mock(UserRepository.class);
        this.userService = new UserService(userRepository);
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(
                new User(1L, "John"),
                new User(2L, "Jane")
        );
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        Assertions.assertEquals(users, result);
    }

    @Test
    void getUserById(){
        User user = new User(1L,"Camilo");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User userId = userService.getUserById(user.getId());

        Assertions.assertSame(user,userId);
    }

}

