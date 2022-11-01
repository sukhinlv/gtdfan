package ru.jsft.gtdfan.priority;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, Integer> {
}
