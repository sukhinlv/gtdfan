package ru.jsft.gtdfan.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.jsft.gtdfan.util.validation.NoHtml;

@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private Integer id;

    @NoHtml
    @NotBlank
    private String name;
}
