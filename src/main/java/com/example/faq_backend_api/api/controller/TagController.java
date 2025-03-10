package com.example.faq_backend_api.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.model.Tag;
import com.example.faq_backend_api.service.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public Tag createTag(@RequestParam String name) {
        return tagService.createTag(name);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
    
}
