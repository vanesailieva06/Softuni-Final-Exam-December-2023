package com.example.bookstore.model.dto;

public class NewCommentDto {

    private String message;
    private String bookTitle;
    private String user;

    public String getMessage() {
        return message;
    }

    public NewCommentDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
