package ru.jsft.gtdfan.priority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.jsft.gtdfan.commonmodel.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Priority extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;

    @NotBlank
    private Integer level;
}
