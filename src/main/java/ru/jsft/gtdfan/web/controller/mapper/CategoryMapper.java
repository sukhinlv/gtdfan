package ru.jsft.gtdfan.web.controller.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.web.controller.dto.CategoryDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    Category toEntity(CategoryDto destination);

    CategoryDto toDto(Category source);
}
