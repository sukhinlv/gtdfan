package ru.jsft.gtdfan.web.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import ru.jsft.gtdfan.model.User;

@Component
public class AggregateUserLongToUserIdConverter {

    public Long map(AggregateReference<User, Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<User, Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
