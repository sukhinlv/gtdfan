package ru.jsft.gtdfan.service;

import org.springframework.stereotype.Service;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }
}
