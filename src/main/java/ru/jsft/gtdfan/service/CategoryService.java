package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.repository.CategoryRepository;

import java.util.Optional;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Iterable<Category> findAll() {
        log.info("Get all categories");
        return repository.findAll();
    }

    public Category findById(long id) {
        log.info("Find category with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("Category with id = %d not found", id))));
    }

    public Category create(Category category) {
        if (!category.isNew()) {
            throw new IllegalArgumentException("Category must be new");
        }

        log.info("Create category: {}", category);
        return repository.save(category);
    }

    public void delete(long id) {
        log.info("Delete category with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Category update(long id, Category category) {
        Optional<Category> categoryOptional = repository.findById(id);

        if (categoryOptional.isEmpty()) {
            throw new NotFoundException(String.format("Category with id = %d not found", id));
        }

        log.info("Update category with id = {}", category.getId());
        category.setId(id);
        return repository.save(category);
    }
}
