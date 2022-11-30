package ru.jsft.gtdfan.controller.dto;

import lombok.*;
import ru.jsft.gtdfan.model.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NoteDto extends BaseEntity {

    @NotNull
    private LocalDateTime edited = LocalDateTime.now();

    @NoHtml
    @NotBlank
    private String noteText;
}
