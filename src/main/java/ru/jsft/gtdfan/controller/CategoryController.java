package ru.jsft.gtdfan.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jsft.gtdfan.controller.dto.CategoryDto;
import ru.jsft.gtdfan.controller.mapper.CategoryMapper;
import ru.jsft.gtdfan.service.CategoryService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = CategoryController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    public static final String REST_URL = "/api/v1/categories";
    private static final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), false)
                .map(mapper::toDto)
                .sorted(Comparator.comparing(CategoryDto::getName))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

}
