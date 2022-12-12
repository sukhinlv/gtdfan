package ru.jsft.gtdfan.model;

import lombok.*;
import ru.jsft.gtdfan.validation.NoHtml;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Priority extends BaseEntity {

    @NoHtml
    @NotBlank(message = "Priority must have name")
    private String name;

    @Digits(message = "Level number must be not empty", integer = 128, fraction = 0)
    private Integer level;
}
