package ru.jsft.gtdfan.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriorityDto {
    private Long id;

    @NoHtml
    @NotBlank
    private String name;

    @NotNull
    private Integer level;
}
