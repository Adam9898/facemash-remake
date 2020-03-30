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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UniqueUsernameValidator uniqueUsernameValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
        Role alreadyDefinedRole = roleRepository.findByRole(UserRole.ROLE_REGULAR);
        if (alreadyDefinedRole != null) {
            user.getRoles().add(alreadyDefinedRole);
        } else {
            user.addRoles(UserRole.ROLE_REGULAR);
        }
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void startNewSession(String username, String password, HttpServletRequest request) {
        try {
            System.out.println(username);
            System.out.println(password);
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}