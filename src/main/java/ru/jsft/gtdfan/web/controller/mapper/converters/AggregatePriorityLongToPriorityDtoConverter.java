package ru.jsft.gtdfan.web.controller.mapper.converters;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import ru.jsft.gtdfan.model.Priority;
import ru.jsft.gtdfan.service.PriorityService;
import ru.jsft.gtdfan.web.controller.dto.PriorityDto;
import ru.jsft.gtdfan.web.controller.mapper.PriorityMapper;

@Component
public class AggregatePriorityLongToPriorityDtoConverter {
    private final PriorityService service;
    private final PriorityMapper mapper;

    public AggregatePriorityLongToPriorityDtoConverter(PriorityService service, PriorityMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public PriorityDto map(AggregateReference<Priority, Long> value) {
        return value != null && value.getId() != null ? mapper.toDto(service.findById(value.getId())) : null;
    }

    public AggregateReference<Priority, Long> map(PriorityDto value) {
        return value != null && value.getId() != null ? AggregateReference.to(value.getId()) : null;
    }
}
