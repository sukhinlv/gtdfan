package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.web.controller.dto.UserDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java( passwordEncoder.encode(destination.getPassword()) )")
    public abstract User toEntity(UserDto destination);

    @Mapping(target = "password", constant = "***")
    public abstract UserDto toDto(User source);
}
