package com.example.faq_backend_api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.faq_backend_api.api.model.FAQ;

@Service
public class FAQService {

    private List<FAQ> faqList;


    public FAQService() {
        faqList = new ArrayList<>();
        FAQ faq1  = new FAQ(1, "How much money do i earn per hour?", "you will earn 13 boxes per hour", "https://youtube.com");
        FAQ faq2  = new FAQ(2, "How much money do i earn per hour?", "you will earn 13 boxes per hour", "https://youtube.com");
        FAQ faq3  = new FAQ(3, "How much money do i earn per hour?", "you will earn 13 boxes per hour", "https://youtube.com");

        faqList.addAll(Arrays.asList(faq1, faq2,  faq3));
    }

    public Optional<FAQ> getFAQ(int id) {
        for (FAQ faq : faqList) {
            if (id == faq.getId()) {
                return Optional.of(faq);
            }
        }
        return Optional.empty();
    }

    public FAQ addFAQ(FAQ faq) {
        faqList.add(faq);
        return faq;
    }
    
    
}
