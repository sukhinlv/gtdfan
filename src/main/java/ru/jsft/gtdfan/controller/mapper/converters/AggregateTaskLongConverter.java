package ru.jsft.gtdfan.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.model.Task;

public class AggregateTaskLongConverter {
    public Long map(AggregateReference<Task, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<Task, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
