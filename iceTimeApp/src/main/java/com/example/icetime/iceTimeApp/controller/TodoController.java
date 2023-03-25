package com.example.icetime.iceTimeApp.controller;

import com.example.icetime.iceTimeApp.entity.User;
import com.example.icetime.iceTimeApp.repository.ToDoRepository;
import com.example.icetime.iceTimeApp.service.ToDoService;
import com.example.icetime.iceTimeApp.service.UserService;

import jakarta.validation.Valid;

import com.example.icetime.iceTimeApp.entity.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
    private UserService userService;
    private ToDoRepository todoRepository;

    @Autowired
    ToDoService todoService;

    public TodoController(UserService userService, ToDoRepository todoRepository) {
        this.userService = userService;
        this.todoRepository = todoRepository;
    }

    // mostra tutti i todos relativi all'utente
    @GetMapping("/todos")
    public String getTodos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("todos", todoRepository.findByUser(user));
        return "todos/index";
    }

    // reindirizza al form per la creazione di un nuovo todo
    @GetMapping("/todos/create")
    public String createTodo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("event", new ToDo());

        return "/todos/create";
    }

    // richiesta POST per il salvataggio del nuovo todo sul db
    @PostMapping("todos/create-todo")
    public String storeTodo(@Valid @ModelAttribute("todo") ToDo formTodo, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);

        if (bindingResult.hasErrors()) {
            return "/todos/create";
        }
        formTodo.setUser(user);
        todoService.saveOrUpdate(formTodo);
        return "redirect:/todos";
    }

    // reindirizza al form per la modifica di un todo gia esistente
    @GetMapping("todos/edit/{id}")
    public String editTodo(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);

        ToDo todo = todoService.getTodoById(id);
        Long todoId = todo.getId();
        model.addAttribute("todoId", todoId);
        model.addAttribute("todo", todoRepository.findById(id));
        return "/todos/edit";
    }

    // aggiorna il todo apportando le modifiche sul db
    @PostMapping("/todos/edit/{id}")
    public String updateTodo(@Valid @ModelAttribute("event") ToDo formTodo, BindingResult bindingResult,
            @PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model,
            @RequestParam("todoCheck") int checkboxValue) {
        String email = userDetails.getUsername();
        User user = userService.findUserByEmail(email);

        if (bindingResult.hasErrors()) {
            return "/todos/edit";
        }

        /*
         * se l'utente spunta il todo come completato checkboxValue = 1 quindi
         * imposto l'attributo isChecked=true
         * imposto data e ora di completamento
         */
        if (checkboxValue == 1) {
            formTodo.setChecked(true);
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            formTodo.setEndDate(localDate);
            formTodo.setEndTime(localTime);

        } else if (checkboxValue == 2) {
            formTodo.setChecked(false);
        }

        formTodo.setUser(user);
        todoService.saveOrUpdate(formTodo);

        return "redirect:/todos";
    }

    // delete di un todo
    @PostMapping("todos/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);

        return "redirect:/todos";
    }
}
