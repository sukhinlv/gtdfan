package ru.jsft.gtdfan.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jsft.gtdfan.validation.NoHtml;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto {
    private Long id;

    @NoHtml
    @NotBlank(message = "Priority must have name")
    private String name;

    @Digits(message = "Level number must be not empty", integer = 128, fraction = 0)
    private Integer level;
}
