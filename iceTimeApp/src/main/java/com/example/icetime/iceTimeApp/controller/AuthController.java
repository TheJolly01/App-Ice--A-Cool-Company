package com.example.icetime.iceTimeApp.controller;

import com.example.icetime.iceTimeApp.dto.UserDto;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import java.util.List;

@Controller
public class AuthController {

    // metodo per la pagina principale
    @GetMapping("index")
    public String home() {
        return "index";
    }

    // metodo per mostrare il form di login
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
}
