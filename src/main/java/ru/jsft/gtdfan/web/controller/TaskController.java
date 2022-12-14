package ru.jsft.gtdfan.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.service.TaskService;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.TaskMapper;
import ru.jsft.gtdfan.web.security.AuthorizedUser;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = TaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    public static final String REST_URL = "/api/v1/tasks";
    private final TaskService service;
    private final TaskMapper mapper;

    public TaskController(TaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAllForUser(@AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(StreamSupport.stream(service.findAllForUser(user.id()).spliterator(), false)
                .map(mapper::toDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id, user.id())));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto dto, @AuthenticationPrincipal AuthorizedUser user) {
        Task created = service.create(mapper.toEntity(dto), user.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDto(created));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id, @AuthenticationPrincipal AuthorizedUser user) {
        service.delete(id, user.id());
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> update(@PathVariable long id, @Valid @RequestBody TaskDto dto, @AuthenticationPrincipal AuthorizedUser user) {
        return ResponseEntity.ok(mapper.toDto(service.update(id, mapper.toEntity(dto), user.id())));
    }
}
