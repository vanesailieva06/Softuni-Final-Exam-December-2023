package com.example.bookstore.model.dto;

import com.example.bookstore.model.entity.enums.AudienceType;
import com.example.bookstore.model.entity.enums.GenreType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public class BookAddDto {
    private String title;
    private String author;
    private String summary;
    private AudienceType audienceType;
    private List<GenreType> genres;
    private BigDecimal price;

    public BookAddDto() {
    }

    @NotNull
    @Size(min = 2, max = 40, message = "Title must be between 2 and 40 characters")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank(message = "Required author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    @NotNull(message = "Required audience type")
    public AudienceType getAudienceType() {
        return audienceType;
    }

    public void setAudienceType(AudienceType audienceType) {
        this.audienceType = audienceType;
    }

    @NotNull(message = "Required genres")
    public List<GenreType> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreType> genres) {
        this.genres = genres;
    }


    @Positive(message = "Positive price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
