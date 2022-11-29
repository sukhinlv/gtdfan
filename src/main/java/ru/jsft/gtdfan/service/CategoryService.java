package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
}
