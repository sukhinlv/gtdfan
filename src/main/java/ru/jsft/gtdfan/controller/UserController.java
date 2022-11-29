package ru.jsft.gtdfan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        return service.findAllUsers();
    }
}
