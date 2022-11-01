package ru.jsft.gtdfan.priority;

import org.springframework.stereotype.Service;

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
