package com.example.icetime.iceTimeApp.controller;

import com.example.icetime.iceTimeApp.dto.UserDto;
import com.example.icetime.iceTimeApp.entity.User;
import com.example.icetime.iceTimeApp.service.UserService;
import com.example.icetime.iceTimeApp.repository.EventRepository;
import com.example.icetime.iceTimeApp.entity.Event;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private EventRepository eventRepository;

    public AuthController(UserService userService, EventRepository eventRepository) {
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    // metodo per la pagina principale
    @GetMapping("/")
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

    // metodo per gestire la registrazione sul DB
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/login";
    }

    // metodo per gestire la rotta di login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // rotta per la landing page post login
    @GetMapping("/day")
    public String calendar(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        // per visualizzare solo gli eventi dello specifico utente loggato
        model.addAttribute("events", eventRepository.findByUser(user));
        return "day";
    }

}
