package com.example.bookstore.web;

import com.example.bookstore.model.dto.OffersViewDto;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OffersController {
    private final OfferService offerService;
    private final BookService bookService;
    public OffersController(OfferService offerService, BookService bookService) {
        this.offerService = offerService;
        this.bookService = bookService;
    }

    @GetMapping("/offers/all")
    public String all(Model model){
        model.addAttribute("offers", offerService.getAllOffers());
        return "all-offers";
    }
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id){
        bookService.addToCart(id);
        return "redirect:/offers/all";
    }

}
