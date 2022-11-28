package ru.jsft.gtdfan.service;

import org.springframework.stereotype.Service;
import ru.jsft.gtdfan.model.Users;
import ru.jsft.gtdfan.repository.UsersRepository;

@Service
public class UsersService {
    private final UsersRepository repository;

    public UsersService(UsersRepository userRepository) {
        this.repository = userRepository;
    }

    public Iterable<Users> findAllUsers() {
        return repository.findAll();
    }
}
