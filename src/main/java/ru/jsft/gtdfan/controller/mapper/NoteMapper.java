package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.NoteDto;
import ru.jsft.gtdfan.model.Note;

@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);
    Note toEntity(NoteDto destination);
    NoteDto toDto(Note source);
}
