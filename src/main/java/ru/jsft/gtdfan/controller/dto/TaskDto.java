package ru.jsft.gtdfan.controller.dto;

import lombok.*;
import ru.jsft.gtdfan.model.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private LocalDateTime created;

    @NotNull
    private LocalDateTime updated;

    @NotNull
    private CategoryDto categoryDto;

    @NotNull
    private PriorityDto priorityDto;

    private TaskDto supertaskDto;

    @NotNull
    private Long userId;

    List<NoteDto> notes;
}
