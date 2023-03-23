package com.example.icetime.iceTimeApp.service;

import com.example.icetime.iceTimeApp.repository.ToDoRepository;
import com.example.icetime.iceTimeApp.entity.ToDo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    // * salvare o aggiornare un evento
    public void saveOrUpdate(ToDo todo) {
        toDoRepository.save(todo);
    }

}
