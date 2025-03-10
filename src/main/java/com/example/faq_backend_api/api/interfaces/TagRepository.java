package com.example.faq_backend_api.api.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.faq_backend_api.api.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    Tag findByName(String name);
}
