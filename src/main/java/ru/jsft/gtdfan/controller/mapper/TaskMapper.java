package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.TaskDto;
import ru.jsft.gtdfan.controller.mapper.converters.AggregateCategoryLongConverter;
import ru.jsft.gtdfan.controller.mapper.converters.AggregatePriorityLongConverter;
import ru.jsft.gtdfan.controller.mapper.converters.AggregateTaskLongConverter;
import ru.jsft.gtdfan.controller.mapper.converters.AggregateUserLongConverter;
import ru.jsft.gtdfan.model.Task;

@Mapper(uses = {
        AggregateCategoryLongConverter.class,
        AggregatePriorityLongConverter.class,
        AggregateTaskLongConverter.class,
        AggregateUserLongConverter.class
})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(ignore = true, target = "created")
    @Mapping(ignore = true, target = "updated")
    Task toEntity(TaskDto destination);

    @Mapping(source = "categoryId", target = "categoryDto")
    @Mapping(source = "priorityId", target = "priorityDto")
    @Mapping(source = "supertaskId", target = "supertaskDto")
    TaskDto toDto(Task source);
}
