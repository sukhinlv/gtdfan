package ru.jsft.gtdfan.controller.mapper;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.model.Category;

public class AggregateCategoryLongConverter {
    public Long map(AggregateReference<Category, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<Category, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
