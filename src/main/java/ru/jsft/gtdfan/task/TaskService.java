package ru.jsft.gtdfan.task;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Iterable<Task> findAllTasks() {
        return repository.findAll();
    }
}
