package com.example.icetime.iceTimeApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("error")
    public String notFound() {
        return "error";
    }

    @GetMapping("team")
    public String viewTeam() {
        return "team";
    }
}
