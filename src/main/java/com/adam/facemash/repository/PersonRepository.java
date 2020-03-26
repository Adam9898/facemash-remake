package com.adam.facemash.repository;

import com.adam.facemash.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
