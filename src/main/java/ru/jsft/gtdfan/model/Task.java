package ru.jsft.gtdfan.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
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

    @NotNull
    @CreatedDate
    private LocalDateTime created;

    @NotNull
    @LastModifiedDate
    private LocalDateTime updated;

    @NotNull
    private AggregateReference<Category, Long> categoryId;

    @NotNull
    private AggregateReference<Priority, Long> priorityId;

    private AggregateReference<Task, Long> supertaskId;

    @NotNull
    private AggregateReference<User, Long> userId;

    @MappedCollection(idColumn = "TASK_ID", keyColumn = "UPDATED")
    List<Note> notes;
}
