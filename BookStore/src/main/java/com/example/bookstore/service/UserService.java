package com.example.bookstore.service;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.model.dto.UserViewDto;

public interface UserService {
    void register(UserRegisterDto userRegisterDto);

    UserViewDto findById(Long id);
}
