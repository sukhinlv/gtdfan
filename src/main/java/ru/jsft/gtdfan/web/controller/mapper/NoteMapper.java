package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.web.controller.dto.NoteDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NoteMapper {
    Note toEntity(NoteDto destination);

    NoteDto toDto(Note source);
}
