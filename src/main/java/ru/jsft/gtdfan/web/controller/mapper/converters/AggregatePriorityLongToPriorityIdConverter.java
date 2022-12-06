package ru.jsft.gtdfan.web.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import ru.jsft.gtdfan.model.Priority;

@Component
public class AggregatePriorityLongToPriorityIdConverter {

    public Long map(AggregateReference<Priority, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<Priority, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
