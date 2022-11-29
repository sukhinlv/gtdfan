package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jsft.gtdfan.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
