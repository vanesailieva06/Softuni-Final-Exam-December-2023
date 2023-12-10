package com.example.bookstore.service;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.model.dto.UserViewDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void register(UserRegisterDto userRegisterDto);

    UserViewDto findById(Long id);

    User findByUsername(String username);

    void deleteAll();

    List<UserViewDto> getAll();

    void deleteUser(Long id);


}
