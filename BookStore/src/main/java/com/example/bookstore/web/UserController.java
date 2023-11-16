package com.example.bookstore.web;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }



    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !userRegisterDto.getPassword().equals(
                userRegisterDto.getConfirmPassword()
        )){
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto",
                            bindingResult);
            return "redirect:register";
        }

        boolean isExist = userService.findByUsername(userRegisterDto.getUsername()) != null;

        if (isExist){
            return "redirect:register";
        }

        userService.register(userRegisterDto);
        return "redirect:login";
    }

    @ModelAttribute("register")
    public UserRegisterDto userRegisterDto(Model model){
        return new UserRegisterDto();
    }

    @PostMapping("/users/login-error")
    public String onFailure(
            @ModelAttribute("username") String username,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", "true");

        return "login";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "profile";
    }

}


