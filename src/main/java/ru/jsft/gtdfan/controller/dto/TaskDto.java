package ru.jsft.gtdfan.controller.dto;

import lombok.*;
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
public class TaskDto extends AbstractDto {

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
    private Long categoryId;

    @NotNull
    private Long priorityId;

    private Long supertaskId;

    @NotNull
    private Long userId;

    List<NoteDto> notes;
}
