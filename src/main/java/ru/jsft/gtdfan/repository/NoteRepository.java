package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jsft.gtdfan.model.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}
