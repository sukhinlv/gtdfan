package ru.jsft.gtdfan.model;

import lombok.*;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Priority extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;

    @NotNull
    private Integer level;
}
