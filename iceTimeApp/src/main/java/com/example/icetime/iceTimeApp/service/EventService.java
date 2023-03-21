package com.example.icetime.iceTimeApp.service;

import com.example.icetime.iceTimeApp.repository.EventRepository;
import com.example.icetime.iceTimeApp.entity.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    // * funzione per recuperare tutti gli eventi
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        eventRepository.findAll().forEach(events1 -> events.add(events1));
        return events;
    }

    // * recuperare uno specifico evento
    public Event getEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    // * salvare o aggiornare un evento
    public void saveOrUpdate(Event event) {
        eventRepository.save(event);
    }

    // * cancellare uno specifico evento
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    // * aggiornare un record
    public void update(Event event, Long id) {
        eventRepository.save(event);
    }
}
