package com.nttdata.microserviciousuarios.feignclients;

import com.nttdata.microserviciousuarios.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "task-service", url = "http://localhost:8003")
public interface TaskFeignClient {
    @GetMapping("/tasks/byuser/{userId}")
    List<Task> getByUserId(@PathVariable("userId") Integer userId);

    @GetMapping("/tasks/date-range")
    List<Task> getTaskByCreationDateRange(@RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                          @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

}
