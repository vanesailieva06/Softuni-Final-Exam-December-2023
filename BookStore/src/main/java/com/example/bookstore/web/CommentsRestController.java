package com.example.bookstore.web;


import com.example.bookstore.model.dto.CommentViewDto;
import com.example.bookstore.model.dto.NewCommentDto;
import com.example.bookstore.model.validation.ApiError;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentsRestController {

    private final CommentService commentService;
    private final BookService bookService;
    public CommentsRestController(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }


    @GetMapping("/api/comments/{bookId}")
    public ResponseEntity<List<CommentViewDto>> getComments(
            @PathVariable Long bookId,
            Principal principal
    ) {
        return ResponseEntity.ok(
                commentService.getComments(bookId));
    }

    @PostMapping("/api/comments/{bookId}")
    public ResponseEntity<CommentViewDto> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long bookId,
            @RequestBody @Valid NewCommentDto newCommentDto
    ) {
        newCommentDto.setUser(principal.getUsername());
        newCommentDto.setBookTitle(bookService.findById(bookId).getTitle());

        CommentViewDto newComment =
                commentService.createComment(newCommentDto);


        URI locationOfNewComment =
                URI.create(String.format("/api/comments/%s/%s", bookId, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}
