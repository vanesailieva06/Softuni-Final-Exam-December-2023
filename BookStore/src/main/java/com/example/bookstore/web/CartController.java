package com.example.bookstore.web;

import com.example.bookstore.model.dto.BookInCartViewDto;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final BookService bookService;

    public CartController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String cart(Model model){
        model.addAttribute("addedBooks", bookService.findAllAddedInCart());
        return "cart";
    }

}
