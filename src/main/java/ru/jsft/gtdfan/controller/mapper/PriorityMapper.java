package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.PriorityDto;
import ru.jsft.gtdfan.model.Priority;

@Mapper
public interface PriorityMapper {
    PriorityMapper INSTANCE = Mappers.getMapper(PriorityMapper.class);
    Priority toEntity(PriorityDto destination);
    PriorityDto toDto(Priority source);
}
