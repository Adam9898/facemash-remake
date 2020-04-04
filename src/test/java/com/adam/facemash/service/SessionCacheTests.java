package com.adam.facemash.service;

import static org.junit.jupiter.api.Assertions.*;

import com.adam.facemash.dao.User;
import com.adam.facemash.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {SessionCache.class})
public class SessionCacheTests {

    @Autowired
    SessionCache sessionCache;

    @MockBean
    UserRepository userRepository;

    final String USERNAME = "testuser";

    @Test
    @DisplayName("Test that session cache getter method gets back proper security context")
    @WithMockUser(username = "testuser")
    public void securityContextGetterTest() {
        // we want to get back the username which is the argument needed for firstByUsername() repository call.
        when(userRepository.findByUsername(anyString())).thenAnswer(invocation -> {
            User user = new User();
            String username = invocation.getArgument(0);
            System.out.println(username);
            user.setUsername(username);
            return user;
        });
        User currentlyLoggedInUser = sessionCache.getCurrentlyLoggedInUser();
        assertNotNull(currentlyLoggedInUser, "Should not be a User object that is null");
        assertEquals(USERNAME, currentlyLoggedInUser.getUsername());
    }

}
