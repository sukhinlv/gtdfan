package ru.jsft.gtdfan.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends AbstractDto {

    @NotBlank(message = "Category must have a name")
    private String name;
}
