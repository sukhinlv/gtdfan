package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.repository.TaskRepository;

import static ru.jsft.gtdfan.validation.ValidationUtils.checkEntityNotNull;
import static ru.jsft.gtdfan.validation.ValidationUtils.checkNew;

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
        return checkEntityNotNull(repository.findByIdAndUserId(id, userId), id, Task.class);
    }

    public Task create(Task task, long userId) {
        log.info("Create task: {}", task);
        checkNew(task);
        task.setUserId(AggregateReference.to(userId));
        return repository.save(task);
    }

    @Transactional
    public void delete(long id, long userId) {
        log.info("Delete task with id = {}", id);
        checkEntityNotNull(repository.findByIdAndUserId(id, userId), id, Task.class);
        repository.deleteById(id);
    }

    @Transactional
    public Task update(long id, Task task, long userId) {
        log.info("Update task with id = {}", task.getId());
        checkEntityNotNull(repository.findByIdAndUserId(id, userId), id, Task.class);
        task.setId(id);
        return repository.save(task);
    }
}
