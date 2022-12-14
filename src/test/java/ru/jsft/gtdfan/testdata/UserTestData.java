package ru.jsft.gtdfan.testdata;

import ru.jsft.gtdfan.model.Role;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.web.controller.dto.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserTestData {
    public static List<UserDto> USER_DTO_LIST = new ArrayList<>(2002);

    public static User ADMIN = new User("admin@ya.ru", "admin", "admin", "admin", LocalDateTime.now(), true, Role.ADMIN);
    public static UserDto ADMIN_DTO = new UserDto(1L, ADMIN.getEmail(), ADMIN.getFirstName(), ADMIN.getLastName(), "***", ADMIN.getCreated(), ADMIN.isEnabled(), ADMIN.getRole());
    public static User USER = new User("user@ya.ru", "user", "user", "user", LocalDateTime.now(), true, Role.USER);
    public static UserDto USER_DTO = new UserDto(2L, USER.getEmail(), USER.getFirstName(), USER.getLastName(), "***", USER.getCreated(), USER.isEnabled(), USER.getRole());

    static {
        USER_DTO_LIST.add(ADMIN_DTO);
        USER_DTO_LIST.add(USER_DTO);
        USER_DTO_LIST = USER_DTO_LIST.stream().sorted(Comparator.comparing(UserDto::getEmail)).toList();
    }

    public static UserDto getNewDto() {
        return new UserDto(null, "new@gmail.com", "newusername", "newsurname", "***", LocalDateTime.now(), true, Role.USER);
    }

    public static UserDto getUpdatedDto() {
        return new UserDto(
                USER_DTO.getId(),
                USER_DTO.getEmail(),
                USER_DTO.getFirstName(),
                USER_DTO.getLastName(),
                USER_DTO.getPassword(),
                USER_DTO.getCreated(),
                USER_DTO.isEnabled(),
                USER_DTO.getRole()
        );
    }
}
