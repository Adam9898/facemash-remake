package com.adam.facemash.service;

import com.adam.facemash.domain.Role;
import com.adam.facemash.domain.User;
import com.adam.facemash.enums.UserRole;
import com.adam.facemash.repository.RoleRepository;
import com.adam.facemash.repository.UserRepository;
import com.adam.facemash.validation.UniqueUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UniqueUsernameValidator uniqueUsernameValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUniqueUsernameValidator(UniqueUsernameValidator uniqueUsernameValidator) {
        this.uniqueUsernameValidator = uniqueUsernameValidator;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user);
    }

    @Override
    public Map<String, Boolean> usernameChecker(String username) {
        boolean usernameIsValid = uniqueUsernameValidator.isUsernameUnique(username);
        return Collections.singletonMap("usernameIsValid", usernameIsValid);
    }

    @Override
    public void registerUser(User user) {
        if (roleExists(UserRole.REGULAR)) {
            user.getRoles().add(new Role(UserRole.REGULAR));
        } else {
            user.addRoles(UserRole.REGULAR);
        }
        userRepository.save(user);
    }

    private boolean roleExists(UserRole userRole) {
    boolean returnValue = false;
        if (roleRepository.findByRole(userRole.toString()) != null) {
            returnValue = true;
        }
        return returnValue;
    }
}