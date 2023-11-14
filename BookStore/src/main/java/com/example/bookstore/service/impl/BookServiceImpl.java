package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.BookAddDto;
import com.example.bookstore.model.dto.BookInCartViewDto;
import com.example.bookstore.model.dto.OffersViewDto;
import com.example.bookstore.model.entity.Author;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.model.entity.Offer;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.GenreRepository;
import com.example.bookstore.repository.OfferRepository;
import com.example.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final OfferRepository offerRepository;
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository, GenreRepository genreRepository, OfferRepository offerRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void addBook(BookAddDto bookAddDto) {
        Book book = modelMapper.map(bookAddDto, Book.class);
        Author author = new Author();
        author.setName(bookAddDto.getAuthor());
        authorRepository.save(author);

        book.setAuthor(author);
        book.setAddedInCart(false);
        bookAddDto.getGenres().forEach(
                genreType ->
                {
                    Genre genre = new Genre();
                    genre.setGenreType(genreType);
                        book.getGenres().add(genre);
                })
        ;
        bookRepository.save(book);
        genreRepository.saveAll(book.getGenres());
    }

    @Override
    public Book findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle).orElse(null);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }


    @Override
    public boolean isInCart(BookInCartViewDto bookDto) {
        Book book = bookRepository.findByTitle(bookDto.getTitle()).orElseThrow();
        return book.isAddedInCart();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<BookInCartViewDto> findAllAddedInCart() {
        return bookRepository.findAllByAddedInCartIsTrue().stream().map(book -> {
                    BookInCartViewDto map = modelMapper.map(book, BookInCartViewDto.class);
                    map.setAuthor(book.getAuthor().getName());
                    map.setPrice(offerRepository.findByBook_Id(book.getId()).orElseThrow().getPrice());
                    return map;
        }
                ).collect(Collectors.toList());
    }

    @Override
    public void addToCart(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAddedInCart(true);
        bookRepository.updateBook(book);

    }

}
