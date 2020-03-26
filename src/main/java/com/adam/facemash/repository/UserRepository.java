package com.adam.facemash.repository;

import com.adam.facemash.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}