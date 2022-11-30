package ru.jsft.gtdfan.model;

import lombok.*;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Note extends BaseEntity {

    @NotNull
    private LocalDateTime edited = LocalDateTime.now();

    @NoHtml
    @NotBlank
    private String noteText;
}
