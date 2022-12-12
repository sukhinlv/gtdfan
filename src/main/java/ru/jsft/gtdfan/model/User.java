package ru.jsft.gtdfan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.jsft.gtdfan.util.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"password"})
@Table("USERS")
public class User extends BaseEntity {

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonDeserialize(using = PasswordDeserializer.class)
    private String password;

    @NotNull
    @CreatedDate
    private LocalDateTime created;

    private boolean enabled;

    private Set<Role> roles;

    public void setEmail(String email) {
        this.email = StringUtils.hasText(email) ? email.toLowerCase() : null;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
