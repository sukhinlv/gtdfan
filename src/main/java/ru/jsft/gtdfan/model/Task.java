package ru.jsft.gtdfan.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @NotNull
    @MappedCollection(idColumn = "id", keyColumn = "category_id")
    private Category category;

    @NotNull
    @MappedCollection(idColumn = "id", keyColumn = "priority_id")
    private Priority priority;

    @MappedCollection(idColumn = "id", keyColumn = "supertask_id")
    private Task supertask;

    @NotNull
    private AggregateReference<User, Long> userId;

    @MappedCollection(idColumn = "task_id", keyColumn = "id")
    List<Note> notes;
}
