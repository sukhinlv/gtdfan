package ru.jsft.gtdfan.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.jsft.gtdfan.commonmodel.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {

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

    @NotBlank
    private LocalDateTime registered = LocalDateTime.now();

    private boolean enabled = true;
}
