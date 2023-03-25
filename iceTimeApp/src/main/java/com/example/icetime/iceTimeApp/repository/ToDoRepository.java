package com.example.icetime.iceTimeApp.repository;

import com.example.icetime.iceTimeApp.entity.User;
import com.example.icetime.iceTimeApp.entity.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    Iterable<ToDo> findByUser(User user);
}
