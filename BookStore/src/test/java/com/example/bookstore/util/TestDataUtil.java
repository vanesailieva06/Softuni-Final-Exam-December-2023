package com.example.bookstore.util;

import com.example.bookstore.model.entity.Author;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.model.entity.enums.AudienceType;
import com.example.bookstore.model.entity.enums.GenreType;
import com.example.bookstore.model.entity.enums.LiteraturePeriods;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class TestDataUtil {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public Book createBook(String title){
        Genre genre = new Genre();

        genre.setGenreType(GenreType.COMEDY);
        Genre genre1 = new Genre();
        genre1.setGenreType(GenreType.ACTION);
        genreRepository.save(genre);
        genreRepository.save(genre1);

        Author author = new Author();
        author.setName("Test author");
        author.setEraOfWriting(LiteraturePeriods.MODERNISM);
        authorRepository.save(author);


        Book book = new Book();

        book.setFavourite(false);
        book.setAddedInCart(true);
        book.setGenres(Set.of(genre, genre1));
        book.setAuthor(author);
        book.setPrice(BigDecimal.valueOf(20));
        book.setSummary("test sumarryyyy");
        book.setAudienceType(AudienceType.ADULT);
        book.setTitle(title);

       return bookRepository.save(book);
    }

    public void cleanUp(){
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
    }
}


