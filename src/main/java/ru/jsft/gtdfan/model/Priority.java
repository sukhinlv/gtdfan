package ru.jsft.gtdfan.model;

import lombok.*;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Priority extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;

    @NotBlank
    private Integer level;
}
