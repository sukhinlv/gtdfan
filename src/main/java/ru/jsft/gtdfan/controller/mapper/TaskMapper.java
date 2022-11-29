package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.TaskDto;
import ru.jsft.gtdfan.model.Task;

@Mapper(uses = {AggregateUserMapper.class})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    Task toEntity(TaskDto destination);
    TaskDto toDto(Task source);
}
