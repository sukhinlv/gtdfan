package ru.jsft.gtdfan.service;

import org.springframework.stereotype.Service;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.repository.NoteRepository;

@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Iterable<Note> findAllNotes() {
        return repository.findAll();
    }
}
