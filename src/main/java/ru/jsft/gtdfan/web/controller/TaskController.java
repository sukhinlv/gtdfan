package ru.jsft.gtdfan.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.service.TaskService;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.TaskMapper;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = TaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    public static final String REST_URL = "/api/v1/tasks";
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAllCategories() {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), false)
                .map(TaskMapper.INSTANCE::toDto)
                .sorted(Comparator.comparing(TaskDto::getName))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(TaskMapper.INSTANCE.toDto(service.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto dto) {
        Task created = service.create(TaskMapper.INSTANCE.toEntity(dto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(TaskMapper.INSTANCE.toDto(created));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> update(@PathVariable long id, @Valid @RequestBody TaskDto dto) {
        return ResponseEntity.ok(TaskMapper.INSTANCE.toDto(service.update(id, TaskMapper.INSTANCE.toEntity(dto))));
    }
}
