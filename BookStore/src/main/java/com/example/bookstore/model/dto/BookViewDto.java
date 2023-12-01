package com.example.bookstore.model.dto;

import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.enums.GenreType;

import java.util.List;

public class BookViewDto {

    private String title;
    private String author;
    private String genres;
    private String summary;
    private Long id;

    private Boolean favourites;
    private Boolean addedInCart;

    public BookViewDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFavourites() {
        return favourites;
    }

    public void setFavourites(Boolean favourites) {
        this.favourites = favourites;
    }

    public Boolean getAddedInCart() {
        return addedInCart;
    }

    public void setAddedInCart(Boolean addedInCart) {
        this.addedInCart = addedInCart;
    }

    @Override
    public String toString() {
        return "BookViewDto{" +
                "genres='" + genres + '\'' +
                '}';
    }
}
