package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.AddOfferDto;
import com.example.bookstore.model.dto.OffersViewDto;
import com.example.bookstore.model.entity.Book;
import com.example.bookstore.model.entity.Genre;
import com.example.bookstore.model.entity.Offer;
import com.example.bookstore.repository.OfferRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.GenreService;
import com.example.bookstore.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final GenreService genreService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, BookService bookService, GenreService genreService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @Override
    public void addOffer(AddOfferDto addOfferDto) {
        Offer offer = modelMapper.map(addOfferDto, Offer.class);

        offer.setBook(bookService.findByTitle(addOfferDto.getBookTitle()));

        offerRepository.save(offer);
    }

    @Override
    public Set<OffersViewDto> getAllOffers() {
        return offerRepository.findAll().stream().map(offer ->{
                    OffersViewDto offersViewDto = modelMapper.map(offer, OffersViewDto.class);
                    Book book = bookService.findByTitle(offersViewDto.getBookTitle());
                    offersViewDto.setBookId(book.getId());
                    offersViewDto.setAuthor(book.getAuthor().getName());
                    offersViewDto.setGenres(book.getGenres().stream().map(Genre::getGenreType).collect(Collectors.toSet()));
                    offersViewDto.setSummary(book.getSummary());
                    return offersViewDto;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteOffer(Long bookId) {
        Offer offer = offerRepository.findByBook_Id(bookId).orElseThrow();

        offerRepository.deleteById(offer.getId());
    }

}
