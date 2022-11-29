package ru.jsft.gtdfan.controller.mapper;

import org.mapstruct.Mapper;
import ru.jsft.gtdfan.controller.dto.CategoryDto;
import ru.jsft.gtdfan.model.Category;

@Mapper
public interface CategoryMapper {
    Category toEntity(CategoryDto destination);
    CategoryDto toDto(Category source);
}
