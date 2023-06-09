package com.example.icetime.iceTimeApp.controller;

import com.example.icetime.iceTimeApp.entity.Event;
import com.example.icetime.iceTimeApp.service.EventService;
import com.example.icetime.iceTimeApp.repository.EventRepository;
import com.example.icetime.iceTimeApp.repository.UserRepository;
import com.example.icetime.iceTimeApp.entity.User;
import com.example.icetime.iceTimeApp.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private EventRepository eventRepository;

    public EventController(UserService userService, EventRepository eventRepository) {
        this.userService = userService;
        this.eventRepository = eventRepository;
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

    // * CREATE CRUD
    @PostMapping("/events/createevent")
    public String store(@Valid @ModelAttribute("event") Event formEvent, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            return "/events/create";
        }
        formEvent.setUser(user);
        eventService.saveOrUpdate(formEvent);

        return "redirect:/day";

    }

    // * GET per il form di modifica
    @GetMapping("/events/edit/{id}")
    public String edit(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        Event event1 = eventService.getEventById(id);
        Long eventId = event1.getId();
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("eventId", eventId);
        model.addAttribute("event", eventRepository.findById(id));
        return "/events/edit";
    }

    // * UPDATE CRUD
    @PostMapping("/events/edit/{id}")
    public String update(@Valid @ModelAttribute("event") Event formEvent, BindingResult bindingResult,
            @PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);

        if (bindingResult.hasErrors()) {
            return "/events/edit";
        }
        formEvent.setUser(user);
        eventService.saveOrUpdate(formEvent);

        return "redirect:/day";
    }

    // * DELETE CRUD
    @PostMapping("events/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);

        return "redirect:/day";
    }

}
