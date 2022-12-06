package ru.jsft.gtdfan.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.service.NoteService;
import ru.jsft.gtdfan.web.controller.dto.NoteDto;
import ru.jsft.gtdfan.web.controller.mapper.NoteMapper;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = NoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteController {
    public static final String REST_URL = "/api/v1/notes";
    private final NoteService service;
    private final NoteMapper mapper;

    public NoteController(NoteService service, NoteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> findAllCategories() {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), false)
                .map(mapper::toDto)
                .sorted(Comparator.comparing(NoteDto::getNote))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> create(@Valid @RequestBody NoteDto noteDto) {
        Note created = service.create(mapper.toEntity(noteDto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDto(created));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> update(@PathVariable long id, @Valid @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(mapper.toDto(service.update(id, mapper.toEntity(noteDto))));
    }
}
