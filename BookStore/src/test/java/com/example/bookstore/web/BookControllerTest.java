package com.example.bookstore.web;


import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.entity.enums.AudienceType;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.util.TestDataUtil;
import com.example.bookstore.util.TestUserDataUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;
    @Autowired
    private TestUserDataUtil testUserDataUtil;
    @Autowired
    private BookRepository bookRepository;

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
//    @WithMockUser(
//            username = TEST_ADMIN_USERNAME,
//            roles = {"USER", "ADMIN"})
    public void testBuyBookWithAnonymous() throws Exception {
        Book book = testDataUtil.createBook("testttt");
        mockMvc.perform(
           get("/book/buy/{id}", book.getId())
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"))
        ;

        assertEquals(1, bookRepository.count());

    }

    @Test
    @WithMockUser(
            username = TEST_USER_USERNAME)
    public void testBuyBookWithUser() throws Exception {
        Book book = testDataUtil.createBook("testtttt");
        mockMvc.perform(
                        get("/books/buy/{id}", book.getId())
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/after-buying"))
        ;

        assertEquals(0, bookRepository.count());

    }

    @Test
    @WithMockUser(
            username = TEST_ADMIN_USERNAME,
            roles = {"USER", "ADMIN"}
    )
    public void testBuyBookWithAdmin() throws Exception {
        Book book = testDataUtil.createBook("test222");
        mockMvc.perform(
                get("/books/buy/{id}", book.getId())
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/after-buying"))
        ;

        assertEquals(0, bookRepository.count());

    }

    @Test
    @WithMockUser(
            username = TEST_USER_USERNAME)
    public void testBuyMultipleBookWithUser() throws Exception {
        Book book = testDataUtil.createBook("test111");
        Book book1 = testDataUtil.createBook("test22222");
        mockMvc.perform(
                get("/books/buy/{id}", book.getId())
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cart"))
        ;

        assertEquals(1, bookRepository.count());

    }

    @Test
    @WithMockUser(username = TEST_USER_USERNAME)
    public void testBuyAllBooks() throws Exception {
        testDataUtil.createBook("test1");
        testDataUtil.createBook("test2");

        mockMvc.perform(get("/books/buy-all"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/after-buying"));

        assertEquals(bookRepository.count(), 0);
    }

    @Test
    @WithMockUser(username = TEST_ADMIN_USERNAME,roles = {"USER", "ADMIN"})
    public void testAddBookWithAdmin() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/book/add")
                        .param("title", TEST_TITLE)
                        .param("author", "test author")
                        .param("summary", "testtttt")
                        .param("audienceType", AudienceType.ADULT.name())
                        .param("genres", "ROMANCE,HORROR")
                        .param("price", "22")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = TEST_USER_USERNAME)
    public void testAddBookWithUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/book/add")
                        .param("title", TEST_TITLE)
                        .param("author", "test author")
                        .param("summary", "testtttt")
                        .param("audienceType", AudienceType.ADULT.name())
                        .param("genres", "ROMANCE,HORROR")
                        .param("price", "22")
                        .with(csrf())
        ).andExpect(status().is4xxClientError());
    }
    @Test
    public void testAddBookWithAnonymous() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/book/add")
                        .param("title", TEST_TITLE)
                        .param("author", "test author")
                        .param("summary", "testtttt")
                        .param("audienceType", AudienceType.ADULT.name())
                        .param("genres", "ROMANCE,HORROR")
                        .param("price", "22")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));
    }

    @Test
    @WithMockUser(username = TEST_USER_USERNAME)
    public void testAddFavourites() throws Exception {
        Book book = testDataUtil.createBook("test title");

        mockMvc.perform(get("/books/favourites/add/{id}", book.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books/all"));

        assertEquals(bookRepository.findAllByFavouriteIsTrue().size(), 1);
    }
}
