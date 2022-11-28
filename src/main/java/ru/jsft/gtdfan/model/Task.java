package ru.jsft.gtdfan.model;

import lombok.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
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
