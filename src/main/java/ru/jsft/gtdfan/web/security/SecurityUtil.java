package ru.jsft.gtdfan.web.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.jsft.gtdfan.model.User;

import static java.util.Objects.requireNonNull;

@UtilityClass
public class SecurityUtil {

    public static User authUser() {
        return get().getUser();
    }

    public static AuthorizedUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static long authId() {
        return get().getUser().getId();
    }
}
