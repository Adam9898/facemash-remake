package com.adam.facemash.service;

import com.adam.facemash.dao.User;
import com.adam.facemash.repository.RoleRepository;
import com.adam.facemash.repository.UserRepository;
import com.adam.facemash.validation.UniqueUsernameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {UserServiceImpl.class})
public class UserServiceImplTests {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UniqueUsernameValidator uniqueUsernameValidator;

    private User testUser;

    @BeforeEach
    public void init() {
        testUser = new User();
        testUser.setUsername("testuser");
    }


    @Test
    @DisplayName("Test that spring security user loader throws an exception")
    public void usernameLoaderShouldThrowAnException() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("invalid"));
    }

    @Test
    @DisplayName("Test that spring security user loader")
    public void usernameLoaderShouldNOTBeNull() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        assertNotNull(userService.loadUserByUsername(testUser.getUsername()));
    }

    @Test
    @DisplayName("Test that spring security user loader returns a " +
            "user details implementation that has a user with the same username that was passed in the method")
    public void usernameLoaderShouldReturnAUserDetailsImplementation() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser).thenAnswer(invocation -> {
            User returnValue = new User();
            returnValue.setUsername(invocation.getArgument(0));
            return returnValue;
        });
        assertEquals(userService.loadUserByUsername(testUser.getUsername()).getUsername(), testUser.getUsername());
    }

    @Test
    @DisplayName("Test that username checker returns a map with a key named usernameIsValid")
    public void usernameCheckerShouldReturnAMapWithusernameIsValid() {
        when(uniqueUsernameValidator.isUsernameUnique(anyString())).thenReturn(true);
        assertTrue(userService.usernameChecker(testUser.getUsername()).containsKey("usernameIsValid"));
    }

    @Test
    @DisplayName("Test that username checker returns a map with a boolean value")
    public void usernameCheckerShouldReturnAMapWithABooleanValue() {
        when(uniqueUsernameValidator.isUsernameUnique(anyString())).thenReturn(true);
        assertTrue(userService.usernameChecker(testUser.getUsername()).get("usernameIsValid") instanceof Boolean);
    }

    @Test
    @DisplayName("Test that username checker returns a map with a value that is true")
    public void usernameCheckerShouldReturnMapWithTrue() {
        when(uniqueUsernameValidator.isUsernameUnique(anyString())).thenReturn(true);
        assertTrue(userService.usernameChecker(testUser.getUsername()).get("usernameIsValid"));
    }

    @Test
    @DisplayName("Test that username checker returns a map with a value that is false")
    public void usernameCheckerShouldReturnMapWithFalse() {
        when(uniqueUsernameValidator.isUsernameUnique(anyString())).thenReturn(false);
        assertFalse(userService.usernameChecker(testUser.getUsername()).get("usernameIsValid"));
    }
}