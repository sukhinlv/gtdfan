package ru.jsft.gtdfan.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryDto extends AbstractDto {

    @NotBlank(message = "Category must have a name")
    private String name;
}
