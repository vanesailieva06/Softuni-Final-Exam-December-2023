package com.example.bookstore.service.impl;

import com.example.bookstore.model.entity.Role;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.entity.enums.RoleType;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.CurrentUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookStoreUserDetailsServiceTest {

    private BookStoreUserDetailsService bookStoreUserDetailsService;

    @Mock
    private UserRepository userMockRepository;
    @Mock
    private CurrentUser currentUser;

    @BeforeEach
    void setUp(){
        bookStoreUserDetailsService = new BookStoreUserDetailsService(userMockRepository, currentUser);
    }

    @Test
    void testUserNotFound(){
        assertThrows(
                UsernameNotFoundException.class,
                () -> bookStoreUserDetailsService.loadUserByUsername("admin")
        );
    }

    @Test
    void testUserFound(){
        User testUser = createNewUser();

        when(userMockRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = bookStoreUserDetailsService.loadUserByUsername(testUser.getUsername());

      assertNotNull(userDetails);

      assertEquals(testUser.getUsername(), userDetails.getUsername());

        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + RoleType.ADMIN),
                "The user is not admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + RoleType.USER),
                "The user is not user");

    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private User createNewUser() {
        User user = new User();
        user.setFullName("Admin Adminov");
        user.setUsername("Admincho");
        user.setEmail("111@12.bg");
        user.setAge(20);
        user.setPassword("1234");
        Role role = new Role();
        role.setRoleType(RoleType.USER);
        Role role1 = new Role();
        role1.setRoleType(RoleType.ADMIN);
        user.setRoles(Set.of(role, role1));
        return user;

    }

}
