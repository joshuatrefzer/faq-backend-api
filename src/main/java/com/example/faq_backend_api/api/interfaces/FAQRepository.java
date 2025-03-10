package com.example.faq_backend_api.api.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.faq_backend_api.api.model.FAQ;



public interface FAQRepository extends JpaRepository<FAQ, Long> {
}