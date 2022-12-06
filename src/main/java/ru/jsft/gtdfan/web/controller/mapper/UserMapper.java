package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.model.User;
import ru.jsft.gtdfan.web.controller.dto.UserDto;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(ignore = true, target = "created")
    User toEntity(UserDto destination);

    UserDto toDto(User source);
}
