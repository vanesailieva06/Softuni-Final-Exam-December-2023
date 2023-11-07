package com.example.bookstore.model.entity;

import com.example.bookstore.model.entity.enums.GenreType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity{
    private GenreType genreType;

    public Genre() {
    }

    @Enumerated(EnumType.STRING)
    public GenreType getGenreType() {
        return genreType;
    }

    public void setGenreType(GenreType genreType) {
        this.genreType = genreType;
    }
}
