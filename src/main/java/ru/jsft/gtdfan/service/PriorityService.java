package ru.jsft.gtdfan.service;

import org.springframework.stereotype.Service;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.repository.PriorityRepository;

@Service
public class PriorityService {
    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public Iterable<Priority> findAllPriorities() {
        return repository.findAll();
    }
}
