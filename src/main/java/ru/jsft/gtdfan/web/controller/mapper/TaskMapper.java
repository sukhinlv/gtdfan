package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregateCategoryLongToCategoryDtoConverter;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregatePriorityLongToPriorityDtoConverter;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregateTaskLongToTaskIdConverter;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregateUserLongToUserIdConverter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                AggregateCategoryLongToCategoryDtoConverter.class,
                AggregatePriorityLongToPriorityDtoConverter.class,
                AggregateTaskLongToTaskIdConverter.class,
                AggregateUserLongToUserIdConverter.class
        })
public interface TaskMapper {
    @Mapping(ignore = true, target = "created")
    @Mapping(ignore = true, target = "updated")
    @Mapping(source = "categoryDto", target = "categoryId")
    @Mapping(source = "priorityDto", target = "priorityId")
    Task toEntity(TaskDto destination);

    @Mapping(source = "categoryId", target = "categoryDto")
    @Mapping(source = "priorityId", target = "priorityDto")
    TaskDto toDto(Task source);
}
