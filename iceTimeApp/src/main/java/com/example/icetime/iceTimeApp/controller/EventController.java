package com.example.icetime.iceTimeApp.controller;

import com.example.icetime.iceTimeApp.entity.Event;
import com.example.icetime.iceTimeApp.service.EventService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    // * recupero di tutti gli eventi
    @GetMapping("/events")
    private List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // * recupero di uno specifico evento
    @GetMapping("/events/{id}")
    private Event getEvents(@PathVariable("id") Long id) {
        return eventService.getEventById(id);
    }

    // * delete di un evento
    @DeleteMapping("/events/{id}")
    private void deleteEvent(@PathVariable("id") Long id) {
        eventService.delete(id);
    }

    // * salvataggio nel DB di un evento
    @PostMapping("/events/create")
    private Long saveEvent(@RequestBody Event event) {
        eventService.saveOrUpdate(event);
        return event.getId();
    }

    // * modifica di un evento
    @PutMapping("/events/update")
    private Event update(@RequestBody Event event) {
        eventService.saveOrUpdate(event);
        return event;
    }
}
