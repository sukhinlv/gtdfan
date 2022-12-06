package ru.jsft.gtdfan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Long id;

    @NotNull
    private Long taskId;

    @NotNull
    private LocalDateTime updated;

    @NoHtml
    @NotBlank
    private String note;
}
