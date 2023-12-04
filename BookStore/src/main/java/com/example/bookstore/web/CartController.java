package com.example.bookstore.web;

import com.example.bookstore.model.dto.BookInCartViewDto;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class CartController {
    private final BookService bookService;

    public CartController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("addedBooks", bookService.findAllAddedInCart());
        model.addAttribute("totalPrice", bookService.findAllAddedInCart()
                .stream().map(BookInCartViewDto::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0)));
        return "cart";
    }

    @GetMapping("/books/buy/{id}")
    public String buyBook(@PathVariable Long id){
        bookService.buyBook(id);
        if (bookService.findAllAddedInCart().size() == 0){
            return "redirect:/after-buying";
        }
        return "redirect:/cart";
    }

    @GetMapping("/books/buy-all")
    public String buyAll(){
        bookService.buyAllBooks();

        return "redirect:/after-buying";
    }
}
