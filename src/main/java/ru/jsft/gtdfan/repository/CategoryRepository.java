package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jsft.gtdfan.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
