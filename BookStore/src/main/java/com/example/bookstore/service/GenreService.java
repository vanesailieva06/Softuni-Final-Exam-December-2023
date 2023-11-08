package com.example.bookstore.service;

import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.model.entity.enums.GenreType;

public interface GenreService {
    Genre findByGenreType(GenreType genreType);
}
