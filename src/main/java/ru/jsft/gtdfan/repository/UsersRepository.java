package ru.jsft.gtdfan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.jsft.gtdfan.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
}
