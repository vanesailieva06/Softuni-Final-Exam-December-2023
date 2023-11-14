package com.example.bookstore.web;

import com.example.bookstore.model.dto.AddOfferDto;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offer")
public class OfferController {
    private final OfferService offerService;
    private final BookService bookService;

    public OfferController(OfferService offerService, BookService bookService) {
        this.offerService = offerService;
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String addOffer(){
        return "add-offer";
    }

    @PostMapping("/add")
    public String addCorrectOffer(@Valid AddOfferDto addOfferDto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || bookService.findByTitle(addOfferDto.getBookTitle())== null){
            redirectAttributes.addFlashAttribute("addOfferDto", addOfferDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferDto",
                            bindingResult);
            return "redirect:addOffer";
        }
        offerService.addOffer(addOfferDto);

        return "redirect:/offers/all";
    }

    @ModelAttribute
    public AddOfferDto addOfferDto(){
        return new AddOfferDto();
    }
}
