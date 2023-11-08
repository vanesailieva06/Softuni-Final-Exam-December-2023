package com.example.bookstore.web;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
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
    public String registerUser(UserRegisterDto userRegisterDto, Model model){

        userService.register(userRegisterDto);

        return "redirect:login";
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


