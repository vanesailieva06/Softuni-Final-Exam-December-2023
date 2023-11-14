package com.example.bookstore.model.dto;

import com.example.bookstore.model.entity.enums.GenreType;

import java.math.BigDecimal;
import java.util.Set;

public class OffersViewDto {
    private String bookTitle;
    private Long bookId;
    private String author;
    private Set<GenreType> genres;
    private String summary;
    private BigDecimal price;

    public OffersViewDto() {
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<GenreType> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreType> genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
