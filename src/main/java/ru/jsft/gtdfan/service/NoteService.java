package ru.jsft.gtdfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.error.NotFoundException;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.repository.NoteRepository;

import java.util.Optional;

@Service
@Slf4j
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Iterable<Note> findAll() {
        log.info("Get all notes");
        return repository.findAll();
    }

    public Note findById(long id) {
        log.info("Find note with id = {}", id);
        return repository.findById(id)
                .orElseThrow(() -> (new NotFoundException(String.format("Note with id = %d not found", id))));
    }

    public Note create(Note note) {
        if (!note.isNew()) {
            throw new IllegalArgumentException("Note must be new");
        }

        log.info("Create note: {}", note);
        return repository.save(note);
    }

    public void delete(long id) {
        log.info("Delete note with id = {}", id);
        repository.deleteById(id);
    }

    @Transactional
    public Note update(long id, Note note) {
        Optional<Note> noteOptional = repository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NotFoundException(String.format("Note with id = %d not found", id));
        }

        log.info("Update note with id = {}", note.getId());
        note.setId(id);
        return repository.save(note);
    }
}
