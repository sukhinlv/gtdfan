package ru.jsft.gtdfan.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Priority extends BaseEntity {

    private String name;

    private Integer level;
}
