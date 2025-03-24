package com.example.faq_backend_api.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.model.FAQ;
import com.example.faq_backend_api.service.FAQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public ResponseEntity<List<FAQ>> getAllFaqs() {
        List<FAQ> faqs = faqService.getAllFAQs();
        return faqs.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(faqs);
    }

    @PostMapping
    public ResponseEntity<FAQ> postFAQ(@RequestBody FAQ faq) {
        FAQ savedFAQ = faqService.addFAQ(faq);
        return ResponseEntity.ok(savedFAQ);
    }

    @PostMapping("/{faqId}/tags")
    public FAQ addTagsToFAQ(@PathVariable Long faqId, @RequestParam Set<String> tags) {
        return faqService.addTagsToFAQ(faqId, tags);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FAQ>> searchFAQs(@RequestParam String query) {
        List<FAQ> foundFaqs = faqService.searchFAQs(query);
        return foundFaqs.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(foundFaqs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQ> updateFAQ(@PathVariable Long id, @RequestBody FAQ updatedFAQ) {
        return faqService.updateFAQ(id, updatedFAQ)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
