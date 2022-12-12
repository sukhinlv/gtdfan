package ru.jsft.gtdfan.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jsft.gtdfan.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;

    private boolean completed;

    @NoHtml
    @NotBlank(message = "Task name must not be empty")
    private String name;

    private LocalDateTime until;

    @NoHtml
    private String link;

    private LocalDateTime created;

    private LocalDateTime updated;

    @NotNull(message = "Category must be set")
    private CategoryDto categoryDto;

    @NotNull(message = "Priority must be set")
    private PriorityDto priorityDto;

    private Long supertaskId;

    @NotNull
    private Long userId;

    List<NoteDto> notes;
}
