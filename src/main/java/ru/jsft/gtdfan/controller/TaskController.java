package ru.jsft.gtdfan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.service.TaskService;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Task> findAllTasks() {
        return service.findAllTasks();
    }
}
