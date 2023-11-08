package com.example.bookstore.service;

import com.example.bookstore.model.entity.Author;

public interface AuthorService {
    Author findByName(String author);
}
