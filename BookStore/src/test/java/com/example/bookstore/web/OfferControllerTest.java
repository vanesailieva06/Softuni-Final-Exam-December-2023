package com.example.bookstore.web;


import com.example.bookstore.repository.OfferRepository;
import com.example.bookstore.util.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private OfferRepository offerRepository;

    @BeforeEach
    public void setUp(){
        testDataUtil.cleanUp();
    }
    @AfterEach
    public void tearDown(){
        testDataUtil.cleanUp();
    }

    private static final String TEST_TITLE = "test title";
    private static final String TEST_ADMIN_USERNAME = "admin";
    private static final String TEST_USER_USERNAME = "user";

    @Test
    @WithMockUser(username = TEST_USER_USERNAME )
    public void testAddOfferWithUser() throws Exception {
        testDataUtil.createBook(TEST_TITLE);
        mockMvc.perform(post("/offer/add")
                .param("bookTitle", TEST_TITLE)
                .param("price", "22")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/all"));

        assertEquals(offerRepository.count(), 1);
    }

    @Test
    @WithMockUser(username = TEST_USER_USERNAME )
    public void testAddOfferWithUserButNotExistingBook() throws Exception {
        mockMvc.perform(post("/offer/add")
                        .param("bookTitle", TEST_TITLE)
                        .param("price", "22")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offer/add"));
    }

    @Test
    public void testAddOfferWithAnonymous() throws Exception {
        mockMvc.perform(post("/offer/add")
                        .param("bookTitle", TEST_TITLE)
                        .param("price", "22")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));
    }
}
