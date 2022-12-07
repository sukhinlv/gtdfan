package ru.jsft.gtdfan.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jsft.gtdfan.util.validation.NoHtml;

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
    @NotBlank
    private String name;

    private LocalDateTime until;

    @NoHtml
    private String link;

    private LocalDateTime created;

    private LocalDateTime updated;

    @NotNull
    private CategoryDto categoryDto;

    @NotNull
    private PriorityDto priorityDto;

    private Long supertaskId;

    @NotNull
    private Long userId;

    List<NoteDto> notes;
}
