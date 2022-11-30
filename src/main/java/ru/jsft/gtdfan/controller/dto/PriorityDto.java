package ru.jsft.gtdfan.controller.dto;

import lombok.*;
import ru.jsft.gtdfan.model.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PriorityDto extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;

    @NotNull
    private Integer level;
}
