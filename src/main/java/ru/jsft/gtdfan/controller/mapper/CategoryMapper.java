package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.controller.dto.CategoryDto;
import ru.jsft.gtdfan.model.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toEntity(CategoryDto destination);
    CategoryDto toDto(Category source);
}
