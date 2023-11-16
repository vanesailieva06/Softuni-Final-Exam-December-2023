package com.example.bookstore.web;

import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/all")
    public String all(Model model){
        model.addAttribute("books", bookService.getAll());

        return "all-books";
    }

    @GetMapping("/books/favourites/add/{id}")
    public String addFavourites(@PathVariable Long id, Model model){
        bookService.addToFavourites(id);
        return "redirect:/books/all";
    }

    @GetMapping("/books/favourites")
    public String favourites(Model model){
        model.addAttribute("booksFavourites", bookService.getBookFavourites());

        return "favourites";
    }

    @GetMapping("/cart/add-from-favourites/{id}")
    public String addToCartFromFavourites(@PathVariable Long id){
        bookService.addToCart(id);
        return "redirect:/books/favourites";
    }


}
