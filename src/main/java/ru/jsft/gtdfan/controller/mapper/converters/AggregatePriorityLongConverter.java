package ru.jsft.gtdfan.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.model.Priority;

public class AggregatePriorityLongConverter {
    public Long map(AggregateReference<Priority, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<Priority, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
