/*package com.adam.facemash.service;

import com.adam.facemash.domain.Role;
import com.adam.facemash.domain.User;
import com.adam.facemash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user);
    }

    @Override
    public String registerUser(User user) {
        String returnVal = "ok";
        User checkUser = userRepository.findByUserName(user.getUsername());

        if (checkUser != null) {
            returnVal = "alreadyExists";
        }

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            userToRegister.addRoles(USER_ROLE);
        }

        user.setEnabled = true;
        userRepository.save(user);
    }
}
*/