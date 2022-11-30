package ru.jsft.gtdfan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.jsft.gtdfan.controller.dto.UserDto;
import ru.jsft.gtdfan.controller.mapper.UserMapper;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String REST_URL = "/api/v1/users";
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllCategories() {
        return ResponseEntity.ok(StreamSupport.stream(service.findAll().spliterator(), false)
                .map(UserMapper.INSTANCE::toDto)
                .sorted(Comparator.comparing(UserDto::getName))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(service.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        User created = service.create(UserMapper.INSTANCE.toEntity(userDto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(UserMapper.INSTANCE.toDto(created));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> update(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(service.update(id, UserMapper.INSTANCE.toEntity(userDto))));
    }
}
