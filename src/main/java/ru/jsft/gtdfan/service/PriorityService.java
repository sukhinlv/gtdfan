package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.repository.PriorityRepository;

import static ru.jsft.gtdfan.validation.ValidationUtils.checkEntityNotNull;
import static ru.jsft.gtdfan.validation.ValidationUtils.checkNew;

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
        return checkEntityNotNull(repository.findById(id), id, Priority.class);
    }

    public Priority create(Priority priority) {
        log.info("Create priority: {}", priority);
        checkNew(priority);
        return repository.save(priority);
    }

    public void delete(long id) {
        log.info("Delete priority with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Priority update(long id, Priority priority) {
        log.info("Update priority with id = {}", priority.getId());
        checkEntityNotNull(repository.findById(id), id, Priority.class);
        priority.setId(id);
        return repository.save(priority);
    }
}
