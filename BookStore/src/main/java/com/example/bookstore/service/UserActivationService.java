package com.example.bookstore.service;

import com.example.bookstore.model.event.UserRegisteredEvent;

public interface UserActivationService {
    void userRegistered(UserRegisteredEvent event);


    String createActivationCode(String userEmail);
}
