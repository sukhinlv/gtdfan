package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Iterable<Task> findAllForUser(long userId) {
        log.info("Get all tasks for userId = {}", userId);
        return repository.findAllByUserId(userId);
    }

    public Task findById(long id, long userId) {
        log.info("Find task with id = {}", id);
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> (new NotFoundException(String.format("Task with id = %d not found", id))));
    }

    public Task create(Task task, long userId) {
        if (!task.isNew()) {
            throw new IllegalArgumentException("Task must be new");
        }

        log.info("Create task: {}", task);
        task.setUserId(AggregateReference.to(userId));
        return repository.save(task);
    }

    @Transactional
    public void delete(long id, long userId) {
        Optional<Task> storedTask = repository.findById(id);

        if (storedTask.isEmpty() || Objects.requireNonNull(storedTask.get().getUserId().getId()) != userId) {
            throw new NotFoundException(String.format("Task with id = %d not found", id));
        }

        log.info("Delete task with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Task update(long id, Task task, long userId) {
        Optional<Task> storedTask = repository.findById(id);

        if (storedTask.isEmpty() || Objects.requireNonNull(storedTask.get().getUserId().getId()) != userId) {
            throw new NotFoundException(String.format("Task with id = %d not found", id));
        }

        log.info("Update task with id = {}", task.getId());
        task.setId(id);
        return repository.save(task);
    }
}
