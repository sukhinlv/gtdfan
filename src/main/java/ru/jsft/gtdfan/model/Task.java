package ru.jsft.gtdfan.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;

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

    private String name;

    private LocalDateTime until;

    private String link;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    private AggregateReference<Category, Long> categoryId;

    private AggregateReference<Priority, Long> priorityId;

    private AggregateReference<Task, Long> supertaskId;

    private AggregateReference<User, Long> userId;

    @MappedCollection(idColumn = "task_id", keyColumn = "updated")
    List<Note> notes;
}
