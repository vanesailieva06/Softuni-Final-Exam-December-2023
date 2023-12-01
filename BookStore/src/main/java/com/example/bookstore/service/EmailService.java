package com.example.bookstore.service;

public interface EmailService {
    void sendRegistrationEmail(String userEmail, String userName, String activationCode);
}
