package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.TaskDto;
import ru.jsft.gtdfan.model.Task;

@Mapper(uses = {AggregateUserLongConverter.class})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(ignore = true, target = "created")
    @Mapping(ignore = true, target = "updated")
    Task toEntity(TaskDto destination);
    TaskDto toDto(Task source);
}
