package ru.jsft.gtdfan.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import ru.jsft.gtdfan.model.Role;
import ru.jsft.gtdfan.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Size(max = 128)
    @NoHtml
    @Email(message = "Please enter valid e-mail")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @Size(max = 128)
    @NoHtml
    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @Size(max = 128)
    @NoHtml
    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @Size(max = 256)
    @NotBlank(message = "Password must not be empty")
    private String password;

    private LocalDateTime created;

    private boolean enabled = true;

    @NotBlank(message = "Role must not be set")
    private Role role;

    public void setEmail(String email) {
        this.email = StringUtils.hasText(email) ? email.toLowerCase() : null;
    }
}
