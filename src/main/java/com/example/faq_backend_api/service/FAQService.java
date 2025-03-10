package com.example.faq_backend_api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.faq_backend_api.api.interfaces.FAQRepository;
import com.example.faq_backend_api.api.model.FAQ;

@Service
public class FAQService {

    private final FAQRepository faqRepository;


    public FAQService(FAQRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public FAQ addFAQ(FAQ faq) {
        if (this.questionIsEmpty(faq)) {
            throw new IllegalArgumentException("question and answer are required fields!");
        }
        return faqRepository.save(faq);
    }

    public Optional<FAQ> getFAQ(Long id) {
        return faqRepository.findById(id);
    }


    Boolean questionIsEmpty(FAQ faq){
        return faq.getQuestion() == "" || faq.getAnswer() == "";
    }
    
    
}
