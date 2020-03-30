package com.adam.facemash.repository;

import com.adam.facemash.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query(value = "SELECT id FROM people WHERE id NOT IN (SELECT person_id FROM users_people WHERE user_id = :userid)",
            nativeQuery = true)
    List<Person> getUserSpecificAvailablePeople(@Param("userid") Long userId);
}