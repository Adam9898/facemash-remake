package com.adam.facemash.service;

import com.adam.facemash.dao.Role;
import com.adam.facemash.dao.User;
import com.adam.facemash.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@SpringBootTest(classes = {UserDetailsImpl.class, User.class})
public class UserDetailsImplTests {


    UserDetailsImpl userDetailsImpl;
    User testUser;
    Set<Role> roles;

    @BeforeEach
    public void init() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("secret");
        roles = new HashSet<>();
        roles.add(new Role(UserRole.ROLE_REGULAR));
        testUser.setRoles(roles);
        userDetailsImpl = new UserDetailsImpl(testUser);
    }

    @Test
    @DisplayName("Test that the role-authority conversion return value has improper length")
    public void getAuthoritiesInvalidLengthTest() {
        assertNotEquals(2, userDetailsImpl.getAuthorities().size(),
                "Should have inproper collection size");
    }

    @Test
    @DisplayName("Test that the role-authority conversion return value has proper length")
    public void getAuthoritiesLengthTest() {
        assertEquals(1, userDetailsImpl.getAuthorities().size(),
                "Should have the proper collection size");
    }

    @Test
    @DisplayName("Test that the role-authority conversion return value is NOT null")
    public void getAuthoritiesNotNullTest() {
        assertNotNull(userDetailsImpl.getAuthorities(), "Should NOT be null");
    }

    @Test
    @DisplayName("Test that the role-authority conversion collection's first value is NOT null")
    public void getAuthoritiesRoleShouldNOTBeNull() {
        assertNotNull(userDetailsImpl.getAuthorities().toArray()[0]);
    }

    @Test
    @DisplayName("Test that authority collection contains a granted authority")
    public void getAuthoritiesCollectionGrantedAuthorityTest() {
        assertTrue(userDetailsImpl.getAuthorities().toArray()[0] instanceof GrantedAuthority);
    }

    @Test
    @DisplayName("Test that authority collection contains a simple granted authority")
    public void getAuthoritiesCollectionSimpleGrantedAuthorityTest() {
        assertTrue(checkIfObjIsSimpleGrantedAuthority(userDetailsImpl.getAuthorities().toArray()[0]));
    }

    private boolean checkIfObjIsSimpleGrantedAuthority (Object obj) {
        return obj instanceof SimpleGrantedAuthority;
    }

    @Test
    @DisplayName("Test that that authority collection contains a simple granted authority with the name: ROLE_REGULAR")
    public void getAuthoritiesCollectionShouldHaveUserRole() {
        assumeTrue(checkIfObjIsSimpleGrantedAuthority(userDetailsImpl.getAuthorities().toArray()[0]));
        SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) userDetailsImpl.getAuthorities().toArray()[0];
        assertEquals(UserRole.ROLE_REGULAR.toString(), simpleGrantedAuthority.getAuthority());
    }

    @Test
    @DisplayName("Test that username getter returns proper value")
    public void getUsernameTest() {
        assertEquals("testuser", userDetailsImpl.getUsername());
    }

    @Test
    @DisplayName("Test that password getter returns proper value")
    public void getPasswordTest() {
        assertEquals("secret", userDetailsImpl.getPassword());
    }

    @Test
    @DisplayName("Test that account non expired returns true")
    public void IsAccountNonExpiredShouldReturnTrue() {
        assertTrue(userDetailsImpl.isAccountNonExpired());
    }

    @Test
    @DisplayName("Test that account non locked returns true")
    public void IsAccountNonLockedShouldReturnTrue() {
        assertTrue(userDetailsImpl.isAccountNonLocked());
    }

    @Test
    @DisplayName("Test that credentials non expired returns true")
    public void IsCredentialsNonExpiredShouldReturnTrue() {
        assertTrue(userDetailsImpl.isCredentialsNonExpired());
    }

    @Test
    @DisplayName("Test that account is enabled return true")
    public void IsEnabledShouldReturnTrue() {
        assertTrue(userDetailsImpl.isEnabled());
    }

}
