package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.web.controller.dto.PriorityDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriorityMapper {
    Priority toEntity(PriorityDto destination);

    PriorityDto toDto(Priority source);
}
