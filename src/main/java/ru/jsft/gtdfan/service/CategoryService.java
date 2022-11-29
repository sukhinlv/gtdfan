package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.repository.CategoryRepository;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAllCategories() {
        log.info("Get all categories");
        return repository.findAll();
    }

    public Category findById(long id) {
        log.info("Find category with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("Category with id = %d not found", id))));
    }
}
