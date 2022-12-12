package ru.jsft.gtdfan.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Note {
    private LocalDateTime updated;

    private String note;
}
