package ru.jsft.gtdfan.model;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Note extends BaseEntity {

    @NotNull
    private Long taskId;

    @NotNull
    @LastModifiedDate
    private LocalDateTime updated;

    @NoHtml
    @NotBlank
    private String note;
}
