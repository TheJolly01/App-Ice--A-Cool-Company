package com.example.icetime.iceTimeApp.controller;

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
}
