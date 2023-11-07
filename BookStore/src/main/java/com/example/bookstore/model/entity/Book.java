package com.example.bookstore.model.entity;

import com.example.bookstore.model.entity.enums.AudienceType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{
    private String title;
    private BigDecimal price;
    private String summary;
    private AudienceType audienceType;
    private Set<Genre> genres;
    private Author author;


    public Book() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(columnDefinition = "TEXT")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Enumerated(EnumType.STRING)
    public AudienceType getAudienceType() {
        return audienceType;
    }

    public void setAudienceType(AudienceType audienceType) {
        this.audienceType = audienceType;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @ManyToOne
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
