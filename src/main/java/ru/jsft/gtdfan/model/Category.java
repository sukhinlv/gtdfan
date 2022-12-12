package ru.jsft.gtdfan.model;

import lombok.*;
import ru.jsft.gtdfan.validation.NoHtml;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Category extends BaseEntity {

    @NoHtml
    @NotBlank(message = "Category must have name")
    private String name;
}
