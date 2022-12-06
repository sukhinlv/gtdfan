package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.web.controller.dto.UserDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    @Mapping(ignore = true, target = "created")
    User toEntity(UserDto destination);

    UserDto toDto(User source);
}
