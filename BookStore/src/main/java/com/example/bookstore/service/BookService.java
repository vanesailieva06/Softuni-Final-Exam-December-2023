package com.example.bookstore.service;

import com.example.bookstore.model.dto.BookAddDto;
import com.example.bookstore.model.dto.BookInCartViewDto;
import com.example.bookstore.model.dto.BookViewDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.enums.GenreType;

import java.util.List;

public interface BookService {
    void addBook(BookAddDto bookAddDto);

    Book findByTitle(String bookTitle);

    List<BookViewDto> getAll();


    boolean isInCart(BookInCartViewDto bookDto);
    void addToCart(Long id);

    Book findById(Long id);

    BookViewDto findBookById(Long id);

    List<BookInCartViewDto> findAllAddedInCart();

    void addToFavourites(Long id);

    List<BookViewDto> getBookFavourites();

    void buyBook(Long id);

    void buyAllBooks();

    List<BookViewDto> getBooksContainGenre(GenreType genreType);
}
