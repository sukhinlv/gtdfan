package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.model.Note;
import ru.jsft.gtdfan.web.controller.dto.NoteDto;

@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    @Mapping(ignore = true, target = "updated")
    Note toEntity(NoteDto destination);

    NoteDto toDto(Note source);
}
