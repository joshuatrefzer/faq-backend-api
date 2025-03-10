package com.example.faq_backend_api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.model.FAQ;
import com.example.faq_backend_api.service.FAQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/faq")
public class FAQController {

    private FAQService faqService;

    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FAQ> getFAQ(@PathVariable Long id) {
        return faqService.getFAQ(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<FAQ> postFAQ(@RequestBody FAQ faq) {
        FAQ savedFAQ = faqService.addFAQ(faq);
        return ResponseEntity.ok(savedFAQ);
    }
}




