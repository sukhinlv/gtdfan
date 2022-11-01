package ru.jsft.gtdfan.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.jsft.gtdfan.commonmodel.BaseEntity;
import ru.jsft.gtdfan.util.validation.NoHtml;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @NoHtml
    @NotBlank
    private String name;

    @NoHtml
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private LocalDateTime registered = LocalDateTime.now();

    private boolean enabled = true;
}
