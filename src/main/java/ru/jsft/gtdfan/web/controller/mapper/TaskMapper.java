package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.Task;
import ru.jsft.gtdfan.web.controller.dto.TaskDto;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregateCategoryLongToCategoryIdConverter;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregatePriorityLongToPriorityIdConverter;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregateTaskLongToTaskIdConverter;
import ru.jsft.gtdfan.web.controller.mapper.converters.AggregateUserLongToUserIdConverter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                AggregateCategoryLongToCategoryIdConverter.class,
                AggregatePriorityLongToPriorityIdConverter.class,
                AggregateTaskLongToTaskIdConverter.class,
                AggregateUserLongToUserIdConverter.class
        })
public interface TaskMapper {

    // TODO: implement mapping from categoryId, priorityId to DTOs

    @Mapping(ignore = true, target = "created")
    @Mapping(ignore = true, target = "updated")
    Task toEntity(TaskDto destination);

    TaskDto toDto(Task source);
}
