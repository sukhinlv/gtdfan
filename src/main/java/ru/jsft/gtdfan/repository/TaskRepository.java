package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jsft.gtdfan.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
