package ru.jsft.gtdfan.users;

import org.springframework.stereotype.Service;

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
