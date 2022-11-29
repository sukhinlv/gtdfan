package ru.jsft.gtdfan.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends AbstractDto {

    @NotBlank(message = "Category must have a name")
    private String name;
}
