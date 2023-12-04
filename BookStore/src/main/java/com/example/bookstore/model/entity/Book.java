package com.example.bookstore.model.entity;

import com.example.bookstore.model.entity.enums.AudienceType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "`books`")
public class Book extends BaseEntity{
    private String title;
    private String summary;
    private AudienceType audienceType;
    private Set<Genre> genres;
    private Author author;
    private Set<Offer> offers;
    private boolean addedInCart;
    private boolean isFavourite;
    private BigDecimal price;
    public Book() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.REMOVE)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Column
    public boolean isAddedInCart() {
        return addedInCart;
    }

    public void setAddedInCart(boolean addedInCart) {
        this.addedInCart = addedInCart;
    }

    @Column
    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "genres=" + genres +
//                '}';
//    }
}
