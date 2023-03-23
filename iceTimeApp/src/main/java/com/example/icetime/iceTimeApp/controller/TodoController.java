package com.example.icetime.iceTimeApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
    @GetMapping("/todos")
    public String getTodos() {
        return "todos/index";
    }
}
