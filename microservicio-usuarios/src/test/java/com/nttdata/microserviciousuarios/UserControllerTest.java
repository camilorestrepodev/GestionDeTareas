package com.nttdata.microserviciousuarios;

import com.nttdata.microserviciousuarios.controller.UserController;
import com.nttdata.microserviciousuarios.entity.User;
import com.nttdata.microserviciousuarios.feignclients.TaskFeignClient;
import com.nttdata.microserviciousuarios.repository.UserRepository;
import com.nttdata.microserviciousuarios.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskFeignClient taskFeignClient;

    private UserService userService;

    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.userRepository = mock(UserRepository.class);
        this.taskFeignClient = mock(TaskFeignClient.class);
        this.userService = new UserService(userRepository, taskFeignClient);
        this.userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "John");
        User user2 = new User(2L, "Jane");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(expectedUsers);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }

  

    
}
