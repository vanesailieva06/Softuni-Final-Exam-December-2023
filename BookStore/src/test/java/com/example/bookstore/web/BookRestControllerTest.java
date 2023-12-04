package com.example.bookstore.web;

import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private BookService bookService;
    @BeforeEach
    public void setUp(){
        testDataUtil.cleanUp();
    }

    @AfterEach
    public void tearDown(){
        testDataUtil.cleanUp();
    }

    private static final String TEST_USER_USERNAME = "user";

    @Test
    @WithMockUser(username = TEST_USER_USERNAME)
    public void testFetchBooks() throws Exception {
        Book book = testDataUtil.createBook("test1");
        Book book1 = testDataUtil.createBook("test2");
        List<String> expectedGenres = new ArrayList<>();
        book.getGenres().forEach(genre -> expectedGenres.add(genre.getGenreType().name()));
        List<String> expectedGenres1 = new ArrayList<>();
        book1.getGenres().forEach(genre -> expectedGenres1.add(genre.getGenreType().name()));
        mockMvc.perform(get("/books/api")).
        andExpect(jsonPath("[0].title").value("test1")).
                andExpect(jsonPath("[0].author").value("Test author")).
                andExpect(jsonPath("[0].genres", containsInAnyOrder(expectedGenres.toArray()))).
                andExpect(jsonPath("[0].summary").value("test sumarryyyy")).
                andExpect(jsonPath("[1].title").value("test2")).
                andExpect(jsonPath("[1].author").value("Test author")).
                andExpect(jsonPath("[1].genres", containsInAnyOrder(expectedGenres.toArray()))).
                andExpect(jsonPath("[1].summary").value("test sumarryyyy"));
    }
}
