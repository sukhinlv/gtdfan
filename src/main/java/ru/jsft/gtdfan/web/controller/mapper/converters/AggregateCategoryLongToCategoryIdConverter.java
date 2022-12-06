package ru.jsft.gtdfan.web.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import ru.jsft.gtdfan.model.Category;

@Component
public class AggregateCategoryLongToCategoryIdConverter {

    public Long map(AggregateReference<Category, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<Category, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
