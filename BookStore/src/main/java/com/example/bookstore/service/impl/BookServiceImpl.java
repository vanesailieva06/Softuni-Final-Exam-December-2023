package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.BookAddDto;
import com.example.bookstore.model.dto.BookInCartViewDto;
import com.example.bookstore.model.dto.BookViewDto;
import com.example.bookstore.model.entity.Author;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.GenreRepository;
import com.example.bookstore.repository.OfferRepository;
import com.example.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Transactional
    @Override
    public void addBook(BookAddDto bookAddDto) {
        Book book = modelMapper.map(bookAddDto, Book.class);
        Author author = new Author();
        author.setName(bookAddDto.getAuthor());
        authorRepository.save(author);

        book.setAuthor(author);
        book.setAddedInCart(false);
        book.setFavourite(false);
        Set<Genre> newGenres = new HashSet<>();
        bookAddDto.getGenres().forEach(
                genreType ->
                {
                    Genre genre = new Genre();
                    genre.setGenreType(genreType);
                    newGenres.add(genre);
                })
        ;
        book.setGenres(newGenres);
        bookRepository.save(book);
        genreRepository.saveAll(book.getGenres());
    }

    @Override
    public Book findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle).orElse(null);
    }

    @Override
    public List<BookViewDto> getAll() {
        return bookRepository.findAll()
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    private BookViewDto map(Book book) {
        BookViewDto map = modelMapper.map(book, BookViewDto.class);
        map.setFavourites(book.isFavourite());
        map.setAuthor(book.getAuthor().getName());
        List<String> expectedGenres = new ArrayList<>();
        book.getGenres().forEach(genre -> expectedGenres.add(genre.getGenreType().name()));
        map.setGenres(expectedGenres);
        return map;
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
                    map.setPrice(bookRepository.findByIdAndAddedInCartIsTrue(book.getId()).orElseThrow().getPrice()
                    );
                    return map;
        }
                ).collect(Collectors.toList());
    }

    @Override
    public void addToFavourites(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setFavourite(true);
        bookRepository.updateBookAddedInFavourites(book);
    }

    @Override
    public List<BookViewDto> getBookFavourites() {
        return bookRepository.findAllByFavouriteIsTrue()
                .stream().map(book -> {
                    BookViewDto map = modelMapper.map(book, BookViewDto.class);
                    map.setFavourites(book.isFavourite());
                    map.setAddedInCart(book.isAddedInCart());
                    map.setAuthor(book.getAuthor().getName());
                    List<String> expectedGenres = new ArrayList<>();
                    book.getGenres().forEach(genre -> expectedGenres.add(genre.getGenreType().name()));
                    map.setGenres(expectedGenres);

                    return map;

                })
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void buyBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void buyAllBooks() {
        bookRepository.findAllByAddedInCartIsTrue().forEach(book -> bookRepository.deleteById(book.getId()));
    }

    @Override
    @Transactional
    public void addToCart(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();

        book.setAddedInCart(true);
        bookRepository.updateBookAddedInCart(book);

    }

}
