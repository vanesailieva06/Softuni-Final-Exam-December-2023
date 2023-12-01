package com.example.bookstore.web;


import com.example.bookstore.model.entity.User;
import com.example.bookstore.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    private static final String TEST_USER_EMAIL = "pesho@example.com";
    private static final String TEST_USER_AGE = "20";

    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users/register")
                                .param("email", TEST_USER_EMAIL)
                                .param("fullName", "Petar Petrov")
                                .param("username", "pesho")
                                .param("age", TEST_USER_AGE)
                                .param("password", "1234")
                                .param("confirmPassword", "1234")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection());

        assertEquals(userRepository.count(), 1);

        Optional<User> testUser = userRepository.findByEmail(TEST_USER_EMAIL);

        assertTrue(testUser.isPresent());

        User user = testUser.get();

        assertEquals(user.getAge(), Integer.parseInt(TEST_USER_AGE));
    }
}
