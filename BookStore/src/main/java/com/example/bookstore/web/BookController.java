package com.example.bookstore.web;

import com.example.bookstore.model.dto.BookAddDto;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addBook(){
        return "add-book";
    }

    @PostMapping("/add")
    public String addCorrectBook(@Valid BookAddDto bookAddDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("bookAddDto", bookAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookAddDto",
                            bindingResult);
            return "redirect:/book/add";
        }
        bookService.addBook(bookAddDto);

        return "redirect:/books/all";
    }
    @ModelAttribute
    public BookAddDto bookAddDto(){
        return new BookAddDto();
    }
}
