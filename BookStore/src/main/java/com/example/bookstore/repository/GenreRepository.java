package com.example.bookstore.repository;

import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.model.entity.enums.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenreType(GenreType genreType);
}
