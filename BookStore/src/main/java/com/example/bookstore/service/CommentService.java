package com.example.bookstore.service;

import com.example.bookstore.model.dto.CommentViewDto;
import com.example.bookstore.model.dto.NewCommentDto;

import java.util.List;

public interface CommentService {
    List<CommentViewDto> getComments(Long bookId);

    CommentViewDto createComment(NewCommentDto newCommentBindingModel);
}
