package ru.jsft.gtdfan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Category> findAllCategories() {
        return service.findAllCategories();
    }
}
