package ru.jsft.gtdfan.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Note {
    private LocalDateTime updated;

    private String note;
}
