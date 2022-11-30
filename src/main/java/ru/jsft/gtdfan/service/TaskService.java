package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;

import java.util.Optional;

@Service
@Slf4j
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Iterable<Task> findAll() {
        log.info("Get all tasks");
        return repository.findAll();
    }

    public Task findById(long id) {
        log.info("Find task with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("Task with id = %d not found", id))));
    }

    public Task create(Task task) {
        if (!task.isNew()) {
            throw new IllegalArgumentException("Task must be new");
        }

        log.info("Create task: {}", task);
        return repository.save(task);
    }

    public void delete(long id) {
        log.info("Delete task with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Task update(long id, Task task) {
        Optional<Task> taskOptional = repository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new NotFoundException(String.format("Task with id = %d not found", id));
        }

        log.info("Update task with id = {}", task.getId());
        task.setId(id);
        return repository.save(task);
    }
}
