package com.example.faq_backend_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.faq_backend_api.api.interfaces.TagRepository;
import com.example.faq_backend_api.api.model.Tag;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagByName(String name) {
        return Optional.ofNullable(tagRepository.findByName(name));
    }

}
