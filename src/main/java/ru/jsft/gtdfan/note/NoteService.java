package ru.jsft.gtdfan.note;

import org.springframework.stereotype.Service;

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
