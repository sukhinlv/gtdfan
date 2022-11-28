package ru.jsft.gtdfan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.service.NoteService;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Note> findAllNotes() {
        return service.findAllNotes();
    }
}
