package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.repository.UserRepository;

import static ru.jsft.gtdfan.validation.ValidationUtils.checkEntityNotNull;
import static ru.jsft.gtdfan.validation.ValidationUtils.checkNew;

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
        return checkEntityNotNull(repository.findById(id), id, User.class);
    }

    public User create(User user) {
        log.info("Create user: {}", user);
        checkNew(user);
        return repository.save(user);
    }

    public void delete(long id) {
        log.info("Delete user with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public User update(long id, User user) {
        log.info("Update user with id = {}", user.getId());
        User storedUser = checkEntityNotNull(repository.findById(id), id, User.class);
        user.setId(id);
        user.setPassword(storedUser.getPassword()); // do not update the password, it must be updated in a separate way
        user.setRole(storedUser.getRole()); // do not update role, it must be updated in a separate way
        return repository.save(user);
    }
}
