package ru.jsft.gtdfan.priority;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/priority")
public class PriorityController {
    private final PriorityService service;

    public PriorityController(PriorityService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Priority> findAllPriorities() {
        return service.findAllPriorities();
    }
}
