package ru.jsft.gtdfan.category;

import org.springframework.stereotype.Service;

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
