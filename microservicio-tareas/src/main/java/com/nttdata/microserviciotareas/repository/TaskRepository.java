package com.nttdata.microserviciotareas.repository;

import com.nttdata.microserviciotareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Integer userId);
    @Query("SELECT t FROM Task t WHERE t.creationDate BETWEEN :startDate AND :endDate")
    List<Task> findByCreationDateRange(LocalDate startDate, LocalDate endDate);
}
