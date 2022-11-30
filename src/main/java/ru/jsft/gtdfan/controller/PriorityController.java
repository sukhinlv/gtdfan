package ru.jsft.gtdfan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.jsft.gtdfan.controller.dto.PriorityDto;
import ru.jsft.gtdfan.controller.mapper.PriorityMapper;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.service.PriorityService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = PriorityController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PriorityController {
    public static final String REST_URL = "/api/v1/priorities";
    private final PriorityService service;

    public PriorityController(PriorityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PriorityDto>> findAllCategories() {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), false)
                .map(PriorityMapper.INSTANCE::toDto)
                .sorted(Comparator.comparing(PriorityDto::getName))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriorityDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(PriorityMapper.INSTANCE.toDto(service.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriorityDto> create(@Valid @RequestBody PriorityDto mealDto) {
        Priority created = service.create(PriorityMapper.INSTANCE.toEntity(mealDto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(PriorityMapper.INSTANCE.toDto(created));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriorityDto> update(@PathVariable long id, @Valid @RequestBody PriorityDto mealDto) {
        return ResponseEntity.ok(PriorityMapper.INSTANCE.toDto(service.update(id, PriorityMapper.INSTANCE.toEntity(mealDto))));
    }
}
