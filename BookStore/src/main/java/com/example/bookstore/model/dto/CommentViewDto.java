package com.example.bookstore.model.dto;

import java.time.LocalDateTime;

public class CommentViewDto {
    private Long commentId;
    private String message;
    private String user;
    private LocalDateTime created;
    private boolean canApprove;
    private boolean canDelete;

    public Long getCommentId() {
        return commentId;
    }

    public CommentViewDto setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentViewDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentViewDto setUser(String user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentViewDto setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public CommentViewDto setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentViewDto setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
