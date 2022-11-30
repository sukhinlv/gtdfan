package ru.jsft.gtdfan.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import ru.jsft.gtdfan.model.Task;

@Component
public class AggregateTaskLongToTaskIdConverter {

    public Long map(AggregateReference<Task, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<Task, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
