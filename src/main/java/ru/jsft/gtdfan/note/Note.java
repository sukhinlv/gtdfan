package ru.jsft.gtdfan.note;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.jsft.gtdfan.util.validation.NoHtml;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Note {
    @Id
    private Integer id;

    @NotBlank
    private LocalDateTime edited = LocalDateTime.now();

    @NoHtml
    @NotBlank
    private String noteText;
}
