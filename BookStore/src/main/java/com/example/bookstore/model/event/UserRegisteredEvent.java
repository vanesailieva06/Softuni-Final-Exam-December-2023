package com.example.bookstore.model.event;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
    private final String email;
    private final String username;
    public UserRegisteredEvent(Object source, String email, String username) {
        super(source);
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
