package com.adam.facemash.repository;

import com.adam.facemash.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String roleName);
}
