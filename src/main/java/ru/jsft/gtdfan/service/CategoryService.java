package ru.jsft.gtdfan.service;

import org.springframework.stereotype.Service;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAllCategories() {
        return repository.findAll();
    }
}
