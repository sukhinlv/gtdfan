package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.web.controller.dto.PriorityDto;

@Mapper
public interface PriorityMapper {
    PriorityMapper INSTANCE = Mappers.getMapper(PriorityMapper.class);
    Priority toEntity(PriorityDto destination);
    PriorityDto toDto(Priority source);
}
