package com.nttdata.microserviciotareas.repository;

import com.nttdata.microserviciotareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Integer userId);
}
