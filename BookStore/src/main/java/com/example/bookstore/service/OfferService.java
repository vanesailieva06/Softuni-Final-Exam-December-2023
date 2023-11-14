package com.example.bookstore.service;

import com.example.bookstore.model.dto.AddOfferDto;
import com.example.bookstore.model.dto.OffersViewDto;
import com.example.bookstore.model.entity.Book;

import java.util.List;
import java.util.Set;

public interface OfferService {
    void addOffer(AddOfferDto addOfferDto);

    Set<OffersViewDto> getAllOffers();

    void deleteOffer(Long bookId);
}
