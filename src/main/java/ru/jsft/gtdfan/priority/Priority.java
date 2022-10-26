package ru.jsft.gtdfan.priority;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.jsft.gtdfan.util.validation.NoHtml;

@Getter
@Setter
@NoArgsConstructor
public class Priority {
    @Id
    private Integer id;

    @NoHtml
    @NotBlank
    private String name;

    @NotBlank
    private Integer level;
}
