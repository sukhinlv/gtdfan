package ru.jsft.gtdfan.controller.dto;

import lombok.*;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends AbstractDto {

    @NotBlank(message = "Category must have a name")
    @NoHtml
    private String name;
}
