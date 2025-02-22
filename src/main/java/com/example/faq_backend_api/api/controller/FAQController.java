package com.example.faq_backend_api.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FAQController {
    
    @GetMapping("/faq")
    public String hello(@RequestParam(required = false) String param) {
        return "Hello";
    }
}


