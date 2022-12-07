package ru.jsft.gtdfan.web.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import ru.jsft.gtdfan.model.Category;
import ru.jsft.gtdfan.service.CategoryService;
import ru.jsft.gtdfan.web.controller.dto.CategoryDto;
import ru.jsft.gtdfan.web.controller.mapper.CategoryMapper;

@Component
public class AggregateCategoryLongToCategoryDtoConverter {
    private final CategoryService service;
    private final CategoryMapper mapper;

    public AggregateCategoryLongToCategoryDtoConverter(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public CategoryDto map(AggregateReference<Category, Long> value) {
        return value != null && value.getId() != null ? mapper.toDto(service.findById(value.getId())) : null;
    }

    public AggregateReference<Category, Long> map(CategoryDto value) {
        return value != null && value.getId() != null ? AggregateReference.to(value.getId()) : null;
    }
}
