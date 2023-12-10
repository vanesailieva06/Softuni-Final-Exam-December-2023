package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.CommentViewDto;
import com.example.bookstore.model.dto.NewCommentDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Comment;
import com.example.bookstore.repository.CommentRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CommentService;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final UserService userService;
    private final CurrentUser currentUser;
    private Book currentBook;
    private final BookStoreUserDetailsService bookStoreUserDetailsService;
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, BookService bookService, UserService userService, CurrentUser currentUser, BookStoreUserDetailsService bookStoreUserDetailsService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
        this.userService = userService;
        this.currentUser = currentUser;
        this.bookStoreUserDetailsService = bookStoreUserDetailsService;
    }

    @Override
    @Transactional
    public List<CommentViewDto> getComments(Long bookId) {
        this.currentBook = bookService.findById(bookId);
        if (currentBook.getComments().isEmpty()){
            return null;
        }


       return currentBook.getComments().stream().map(comment ->{
                           CommentViewDto map = modelMapper.map(comment, CommentViewDto.class);
                           map.setUser(comment.getUser().getUsername());
                           return map;
                       }
                       )
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewDto createComment(NewCommentDto newCommentDto) {
        Comment comment = modelMapper.map(newCommentDto, Comment.class);

        comment.setCreated(LocalDateTime.now());
        comment.setApproved(false);
        comment.setBook(bookService.findByTitle(newCommentDto.getBookTitle()));
        comment.setUser(userService.findByUsername(newCommentDto.getUser()));

        Comment save = commentRepository.save(comment);
        CommentViewDto map = modelMapper.map(save, CommentViewDto.class);
        map.setUser(comment.getUser().getUsername());
        return map;
    }
}
