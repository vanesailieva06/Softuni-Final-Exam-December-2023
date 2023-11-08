package com.example.bookstore.service.impl;

import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.model.entity.enums.GenreType;
import com.example.bookstore.repository.GenreRepository;
import com.example.bookstore.service.GenreService;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findByGenreType(GenreType genreType) {
        return genreRepository.findByGenreType(genreType).orElse(null);
    }
}
