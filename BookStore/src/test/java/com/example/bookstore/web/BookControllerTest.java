package com.example.bookstore.web;


import com.example.bookstore.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private BookRepository bookRepository;
    @PersistenceContext
    private EntityManager entityManager;

//    @BeforeEach
//    public void setUp() {
//        System.out.println("Schema: " + entityManager.getMetamodel().getEntities());
//
//        bookRepository.deleteAll();
//    }
    @AfterEach
    public void tearDown(){
        bookRepository.deleteAll();
    }

    private static final String TEST_TITLE = "test title";
    private static final String TEST_ADMIN_USERNAME = "admin";
    @Test
    @WithMockUser(
            username = TEST_ADMIN_USERNAME,
            roles = {"USER", "ADMIN"})
    public void testAddBook() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/book/add")
                        .param("title", TEST_TITLE)
                        .param("author", "Author test")
                        .param("summary", "test test test")
                        .param("audienceType", "ADULT")
                        .param("genres", "ROMANCE,FANTASY")
                        .param("price", "20")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());

        assertEquals(bookRepository.count(), 1);
    }
}
