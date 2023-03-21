package com.example.icetime.iceTimeApp.controller;

import com.example.icetime.iceTimeApp.entity.Event;
import com.example.icetime.iceTimeApp.service.EventService;
import com.example.icetime.iceTimeApp.entity.User;
import com.example.icetime.iceTimeApp.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class EventController {

    private UserService userService;

    public EventController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    EventService eventService;

    // * GET per il form di creazione
    @GetMapping("/events/create")
    public String createEvent(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("event", new Event());

        return "/events/create";
    }

    // * salvataggio nel DB di un evento
    @PostMapping("/events/createevent")
    public String store(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("event") Event formEvent,
            Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        formEvent.setUser(user);
        eventService.saveOrUpdate(formEvent);

        return "redirect:/calendar";
    }

}