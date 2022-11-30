package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Iterable<User> findAll() {
        log.info("Get all notes");
        return repository.findAll();
    }

    public User findById(long id) {
        log.info("Find note with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("User with id = %d not found", id))));
    }

    public User create(User note) {
        if (!note.isNew()) {
            throw new IllegalArgumentException("User must be new");
        }

        log.info("Create note: {}", note);
        return repository.save(note);
    }

    public void delete(long id) {
        log.info("Delete note with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public User update(long id, User note) {
        Optional<User> noteOptional = repository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NotFoundException(String.format("User with id = %d not found", id));
        }

        log.info("Update note with id = {}", note.getId());
        note.setId(id);
        return repository.save(note);
    }
}
