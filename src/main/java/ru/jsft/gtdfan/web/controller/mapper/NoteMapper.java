package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.web.controller.dto.NoteDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NoteMapper {
    @Mapping(ignore = true, target = "updated")
    Note toEntity(NoteDto destination);

    NoteDto toDto(Note source);
}
