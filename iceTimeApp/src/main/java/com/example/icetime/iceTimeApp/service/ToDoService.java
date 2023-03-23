package com.example.icetime.iceTimeApp.service;

import com.example.icetime.iceTimeApp.repository.ToDoRepository;
import com.example.icetime.iceTimeApp.entity.ToDo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    // * recuperare uno specifico todo
    public ToDo getTodoById(Long id) {
        return toDoRepository.findById(id).get();
    }

    // * salvare o aggiornare un todo
    public void saveOrUpdate(ToDo todo) {
        toDoRepository.save(todo);
    }

}
