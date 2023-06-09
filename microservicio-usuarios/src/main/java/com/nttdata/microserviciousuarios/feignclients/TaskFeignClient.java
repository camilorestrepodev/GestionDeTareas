package com.nttdata.microserviciousuarios.feignclients;

import com.nttdata.microserviciousuarios.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "task-service", url = "http://localhost:8003")
public interface TaskFeignClient {
    @GetMapping("/tasks/byuser/{userId}")
    List<Task> getByUserId(@PathVariable("userId") Integer userId);

}
