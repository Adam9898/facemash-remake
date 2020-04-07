package com.adam.facemash.repository;

import com.adam.facemash.dao.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
