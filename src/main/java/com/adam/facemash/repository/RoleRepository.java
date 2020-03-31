package com.adam.facemash.repository;

import com.adam.facemash.dao.Role;
import com.adam.facemash.enums.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(UserRole role);
}
