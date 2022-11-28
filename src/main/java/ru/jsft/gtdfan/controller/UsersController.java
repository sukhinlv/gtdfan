package ru.jsft.gtdfan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jsft.gtdfan.model.Users;
import ru.jsft.gtdfan.service.UsersService;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Users> findAllUsers() {
        return service.findAllUsers();
    }
}
