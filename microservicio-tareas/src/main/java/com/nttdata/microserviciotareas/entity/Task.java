package com.nttdata.microserviciotareas.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(defaultValue = "1", description = "ID de la tarea")
    private Long id;
    @Schema(defaultValue = "leer", description = "Titulo de la tarea")
    private String title;
    @Schema(defaultValue= "Leer todas las noches", description = "descripción de la tarea")
    private String description;
    @Schema(defaultValue= "2023-08-10", description = "descripción de la tarea")
    private LocalDate creationDate;
    @Schema(defaultValue= "1", description = "Id del usuario")
    private Integer userId;

    public Task() {
    }

    public Task(String title, String description, LocalDate creationDate, Integer userId) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
