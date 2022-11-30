package ru.jsft.gtdfan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table("users")
public class User extends BaseEntity {

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

    private LocalDateTime registered;

    private boolean enabled;
}
