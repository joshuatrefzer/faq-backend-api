package com.example.faq_backend_api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.model.FAQ;
import com.example.faq_backend_api.service.FAQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class FAQController {

    private FAQService faqService;

    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }
    
    @GetMapping("/faq")
    public ResponseEntity<FAQ> getFAQ(@RequestParam Integer id) {
        return ResponseEntity.ok(faqService.getFAQ(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found")));
    }


    @PostMapping("/faq")
    public FAQ postFAQ(@RequestBody FAQ faq) {
    return faqService.addFAQ(faq);
}

    



}


