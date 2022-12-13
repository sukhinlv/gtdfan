package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.repository.UserRepository;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Iterable<User> findAll() {
        log.info("Get all users");
        return repository.findAll();
    }

    public User findById(long id) {
        log.info("Find user with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("User with id = %d not found", id))));
    }

    public User create(User user) {
        if (!user.isNew()) {
            throw new IllegalArgumentException("User must be new");
        }

        log.info("Create user: {}", user);
        return repository.save(user);
    }

    public void delete(long id) {
        log.info("Delete user with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public User update(long id, User user) {
        User storedUser = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User with id = %d not found", id)));

        log.info("Update user with id = {}", user.getId());
        user.setId(id);
        user.setPassword(storedUser.getPassword()); // do not update the password, it must be updated in a separate way
        user.setRole(storedUser.getRole()); // do not update role, it must be updated in a separate way
        return repository.save(user);
    }
}
