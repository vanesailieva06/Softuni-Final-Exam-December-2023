package com.example.bookstore.service;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.model.dto.UserViewDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.User;

public interface UserService {
    void register(UserRegisterDto userRegisterDto);

    UserViewDto findById(Long id);

    User findByUsername(String username);
}
