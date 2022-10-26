package ru.jsft.gtdfan.task;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.category.Category;
import ru.jsft.gtdfan.note.Note;
import ru.jsft.gtdfan.priority.Priority;
import ru.jsft.gtdfan.users.User;
import ru.jsft.gtdfan.util.validation.NoHtml;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    @Id
    private Integer id;

    private boolean completed;

    @NoHtml
    @NotBlank
    private String name;

    private LocalDateTime until;

    @NoHtml
    private String link;

    @NotBlank
    private LocalDateTime created = LocalDateTime.now();;

    @NotBlank
    private LocalDateTime edited;

    @NotBlank
    private AggregateReference<User, Integer> user;

    @NotBlank
    private AggregateReference<Category, Integer> category;

    @NotBlank
    private AggregateReference<Priority, Integer> priority;

    List<Note> notes;
}
