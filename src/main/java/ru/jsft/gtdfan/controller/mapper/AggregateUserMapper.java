package ru.jsft.gtdfan.controller.mapper;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import ru.jsft.gtdfan.model.User;

public class AggregateUserMapper {
    public Long map(AggregateReference<User,Long> value) {
        return value != null ? value.getId() : null;
    }

    public AggregateReference<User,Long> map(Long value) {
        return value != null ? AggregateReference.to(value) : null;
    }
}
