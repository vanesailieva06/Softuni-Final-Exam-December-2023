package com.example.bookstore.service;

import com.example.bookstore.model.dto.BookAddDto;
import com.example.bookstore.model.dto.BookInCartViewDto;
import com.example.bookstore.model.dto.OffersViewDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Offer;

import java.util.List;
import java.util.Set;

public interface BookService {
    void addBook(BookAddDto bookAddDto);

    Book findByTitle(String bookTitle);

    List<Book> getAll();


    boolean isInCart(BookInCartViewDto bookDto);
    void addToCart(Long id);

    Book findById(Long id);

    List<BookInCartViewDto> findAllAddedInCart();
}
