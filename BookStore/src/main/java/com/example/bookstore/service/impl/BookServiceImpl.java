package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.BookAddDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorService authorService;
    private final GenreService genreService;
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public void addBook(BookAddDto bookAddDto) {
        Book book = modelMapper.map(bookAddDto, Book.class);
        book.setAuthor(authorService.findByName(bookAddDto.getAuthor()));
        bookAddDto.getGenres().forEach(
                genreType -> book.getGenres().add(genreService.findByGenreType(genreType))
        );
        bookRepository.save(book);
    }
}
