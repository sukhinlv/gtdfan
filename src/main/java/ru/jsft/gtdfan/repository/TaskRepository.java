package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.jsft.gtdfan.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllByUserId(long userId);

    Optional<Task> findByIdAndUserId(long id, long userId);
}
