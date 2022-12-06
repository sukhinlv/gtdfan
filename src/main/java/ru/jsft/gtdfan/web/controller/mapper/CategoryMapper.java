package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.web.controller.dto.CategoryDto;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toEntity(CategoryDto destination);
    CategoryDto toDto(Category source);
}
