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
        log.info("Get all notes");
        return repository.findAll();
    }

    public Task findById(long id) {
        log.info("Find note with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("Task with id = %d not found", id))));
    }

    public Task create(Task note) {
        if (!note.isNew()) {
            throw new IllegalArgumentException("Task must be new");
        }

        log.info("Create note: {}", note);
        return repository.save(note);
    }

    public void delete(long id) {
        log.info("Delete note with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Task update(long id, Task note) {
        Optional<Task> noteOptional = repository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NotFoundException(String.format("Task with id = %d not found", id));
        }

        log.info("Update note with id = {}", note.getId());
        note.setId(id);
        return repository.save(note);
    }
}
