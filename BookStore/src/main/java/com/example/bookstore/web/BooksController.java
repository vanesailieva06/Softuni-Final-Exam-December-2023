package com.example.bookstore.web;

import com.example.bookstore.model.dto.BookViewDto;
import com.example.bookstore.model.entity.enums.GenreType;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
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

    @GetMapping("/books/details/{id}")
    public String details(@PathVariable Long id , Model model){
        model.addAttribute("details", bookService.findBookById(id));
        return "details";
    }


    @GetMapping("/books/science-fiction")
    public String scienceFiction(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.SCIENCE_FICTION));
        return "genres-book";
    }

//    @GetMapping("/books/science_fiction/favourites/add/{id}")
//    public String addFavouritesScienceFiction(@PathVariable Long id, Model model){
//        bookService.addToFavourites(id);
//        return "redirect:/books/science-fiction";
//    }

    @GetMapping("/books/romance")
    public String romance(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.ROMANCE));
        return "genres-book";
    }
    @GetMapping("/books/history")
    public String history(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.HISTORY));
        return "genres-book";
    }
    @GetMapping("/books/action")
    public String action(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.ACTION));
        return "genres-book";
    }
    @GetMapping("/books/comedy")
    public String comedy(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.COMEDY));
        return "genres-book";
    }
    @GetMapping("/books/thriller")
    public String thriller(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.THRILLER));
        return "genres-book";
    }
    @GetMapping("/books/fantasy")
    public String fantasy(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.FANTASY));
        return "genres-book";
    }
    @GetMapping("/books/horror")
    public String horror(Model model){
        model.addAttribute("booksGenre", bookService.getBooksContainGenre(GenreType.HORROR));
        return "genres-book";
    }

}
