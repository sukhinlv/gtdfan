package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.repository.PriorityRepository;

import java.util.Optional;

@Service
@Slf4j
public class PriorityService {
    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public Iterable<Priority> findAll() {
        log.info("Get all priorities");
        return repository.findAll();
    }

    public Priority findById(long id) {
        log.info("Find priority with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("Priority with id = %d not found", id))));
    }

    public Priority create(Priority priority) {
        if (!priority.isNew()) {
            throw new IllegalArgumentException("Priority must be new");
        }

        log.info("Create priority: {}", priority);
        return repository.save(priority);
    }

    public void delete(long id) {
        log.info("Delete priority with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Priority update(long id, Priority priority) {
        Optional<Priority> priorityOptional = repository.findById(id);

        if (priorityOptional.isEmpty()) {
            throw new NotFoundException(String.format("Priority with id = %d not found", id));
        }

        log.info("Update priority with id = {}", priority.getId());
        priority.setId(id);
        return repository.save(priority);
    }
}
