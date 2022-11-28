package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jsft.gtdfan.model.Priority;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, Integer> {
}
