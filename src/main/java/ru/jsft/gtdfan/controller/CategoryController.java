package ru.jsft.gtdfan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.jsft.gtdfan.controller.dto.CategoryDto;
import ru.jsft.gtdfan.controller.mapper.CategoryMapper;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.service.CategoryService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = CategoryController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    public static final String REST_URL = "/api/v1/categories";
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), false)
                .map(CategoryMapper.INSTANCE::toDto)
                .sorted(Comparator.comparing(CategoryDto::getName))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toDto(service.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
        Category created = service.create(CategoryMapper.INSTANCE.toEntity(categoryDto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(CategoryMapper.INSTANCE.toDto(created));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> update(@PathVariable long id, @Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toDto(service.update(id, CategoryMapper.INSTANCE.toEntity(categoryDto))));
    }
}
