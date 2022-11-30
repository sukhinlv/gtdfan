package ru.jsft.gtdfan.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.jsft.gtdfan.model.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;

    @NoHtml
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    private LocalDateTime registered;

    private boolean enabled = true;
}
