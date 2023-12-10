package com.example.bookstore.web;

import com.example.bookstore.model.dto.NewCommentDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Comment;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CommentRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("admin")
public class CommentRestControllerTest {

    private static final String COMMENT_1 = "hey Spring is cool!";
    private static final String COMMENT_2 = "Well... it is a bit trick sometimes... :(";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataUtil testDataUtil;

    private User testUser;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setPassword("password");
        testUser.setUsername("lucho");
        testUser.setEmail("lucho@example.com");
        testUser.setFullName("lucho balev");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        userRepository.deleteAll();
        testDataUtil.cleanUp();
    }

    @Test
    public void testGetComments() throws Exception {
        var book = testDataUtil.createBook("testTitle");
        initComments(book);

        mockMvc.perform(get("/api/comments/"+ book.getId())).
                andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "lucho")
    public void testCreateComments() throws Exception {
        NewCommentDto testComment = new NewCommentDto().
                setMessage(COMMENT_1);
        testComment.setUser(testUser.getUsername());
        testComment.setMessage(COMMENT_1);
        var emptyBook = testDataUtil.createBook("test title");
        mockMvc.perform(
                        post("/api/comments/" + emptyBook.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testComment))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/comments/"+ emptyBook.getId() + "/" + commentRepository.findByMessage(COMMENT_1).get().getId())))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }


    private Book initComments(Book book) {

        Comment comment1 = new Comment();
        comment1.setCreated(LocalDateTime.now());
        comment1.setUser(testUser);
        comment1.setMessage(COMMENT_1);
        comment1.setApproved(true);
        comment1.setBook(book);

        Comment comment2 = new Comment();
        comment2.setCreated(LocalDateTime.now());
        comment2.setUser(testUser);
        comment2.setMessage(COMMENT_2);
        comment2.setApproved(true);
        comment2.setBook(book);
        book.setComments(Set.of(comment1, comment2));

        return bookRepository.save(book);
    }
}
