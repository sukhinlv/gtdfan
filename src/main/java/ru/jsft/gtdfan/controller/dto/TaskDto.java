package ru.jsft.gtdfan.controller.dto;

import lombok.*;
import ru.jsft.gtdfan.model.*;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TaskDto extends BaseEntity {

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
    private Category category;

    @NotBlank
    private Priority priority;

    private Task supertask;

    @NotBlank
    private Long userId;

    @NotBlank
    List<Note> notes;
}
