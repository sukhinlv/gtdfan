package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.TaskDto;
import ru.jsft.gtdfan.controller.mapper.converters.AggregateCategoryLongToCategoryIdConverter;
import ru.jsft.gtdfan.controller.mapper.converters.AggregatePriorityLongToPriorityIdConverter;
import ru.jsft.gtdfan.controller.mapper.converters.AggregateTaskLongToTaskIdConverter;
import ru.jsft.gtdfan.controller.mapper.converters.AggregateUserLongToUserIdConverter;
import ru.jsft.gtdfan.model.Task;

@Mapper(uses = {
        AggregateCategoryLongToCategoryIdConverter.class,
        AggregatePriorityLongToPriorityIdConverter.class,
        AggregateTaskLongToTaskIdConverter.class,
        AggregateUserLongToUserIdConverter.class
})
public interface TaskMapper {

    // TODO: implement mapping from categoryId, priorityId to DTOs

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(ignore = true, target = "created")
    @Mapping(ignore = true, target = "updated")
    Task toEntity(TaskDto destination);

    TaskDto toDto(Task source);
}
