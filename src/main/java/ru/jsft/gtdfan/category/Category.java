package ru.jsft.gtdfan.category;

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
public class Category extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;
}
