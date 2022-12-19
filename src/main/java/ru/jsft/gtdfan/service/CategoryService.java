package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.repository.CategoryRepository;

import static ru.jsft.gtdfan.validation.ValidationUtils.checkEntityNotNull;
import static ru.jsft.gtdfan.validation.ValidationUtils.checkNew;

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
        return checkEntityNotNull(repository.findById(id), id, Category.class);
    }

    public Category create(Category category) {
        log.info("Create category: {}", category);
        checkNew(category);
        return repository.save(category);
    }

    public void delete(long id) {
        log.info("Delete category with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Category update(long id, Category category) {
        log.info("Update category with id = {}", category.getId());
        checkEntityNotNull(repository.findById(id), id, Category.class);
        category.setId(id);
        return repository.save(category);
    }
}
