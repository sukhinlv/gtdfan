package ru.jsft.gtdfan.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.jsft.gtdfan.category.Category;
import ru.jsft.gtdfan.commonmodel.BaseEntity;
import ru.jsft.gtdfan.note.Note;
import ru.jsft.gtdfan.priority.Priority;
import ru.jsft.gtdfan.users.Users;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {

    private boolean completed;

    @NoHtml
    @NotBlank
    private String name;

    private LocalDateTime until;

    @NoHtml
    private String link;

    @NotBlank
    private LocalDateTime created;

    @NotBlank
    private LocalDateTime edited;

    @NotBlank
    private AggregateReference<Category, Integer> categoryId;

    @NotBlank
    private AggregateReference<Priority, Integer> priorityId;

    @NotBlank
    private AggregateReference<Task, Integer> supertaskId;

    @NotBlank
    @MappedCollection(idColumn = "id", keyColumn = "user_id")
    private Users user;
//    @NotBlank
//    private AggregateReference<Users, Integer> userId;

    @MappedCollection(idColumn = "task_id", keyColumn = "id")
    @NotBlank
    List<Note> notes;
}
