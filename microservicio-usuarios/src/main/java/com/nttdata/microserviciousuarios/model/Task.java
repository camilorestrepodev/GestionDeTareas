package com.nttdata.microserviciousuarios.model;

import io.swagger.v3.oas.annotations.media.Schema;


import java.time.LocalDate;

public class Task {
    @Schema(defaultValue = "leer", description = "Titulo de la tarea")
    private String title;
    @Schema(defaultValue= "Leer todas las noches", description = "descripción de la tarea")
    private String description;
    @Schema(defaultValue= "2023-08-10", description = "descripción de la tarea")
    private LocalDate creationDate;
    private Integer userId;

    public Task() {
    }

    public Task(String title, String description, LocalDate creationDate, Integer userId) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
