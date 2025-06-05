package com.example.taskFlow.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class BaseController {
    
    @GetMapping("/")
    public String hello() {
        return "TaskFlow API";
    }
}
