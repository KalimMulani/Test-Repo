package com.example.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class health_check {
    @GetMapping("/health-check")
    public String health_check() {
        return "OK";
    }
}
