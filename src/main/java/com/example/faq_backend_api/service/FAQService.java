package com.example.faq_backend_api.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.faq_backend_api.api.interfaces.FAQRepository;
import com.example.faq_backend_api.api.model.FAQ;
import com.example.faq_backend_api.api.model.Tag;

@Service
public class FAQService {

    private final FAQRepository faqRepository;
    private final TagService tagService;

    public FAQService(FAQRepository faqRepository, TagService tagService) {
        this.faqRepository = faqRepository;
        this.tagService = tagService;
    }

    public FAQ addTagsToFAQ(Long faqId, Set<String> tagNames) {
        Optional<FAQ> faq = faqRepository.findById(faqId);
        if (faq.isPresent()) {
            Set<Tag> tags = new HashSet<>();
            for (String tagName : tagNames) {
                tagService.getTagByName(tagName)
                        .ifPresent(tags::add);
            }
            FAQ faqToUpdate = faq.get();
            faqToUpdate.setTags(tags);
            return faqRepository.save(faqToUpdate);
        }
        return null;
    }

    public FAQ addFAQ(FAQ faq) {
        if (this.faqQuestionOrAnswerIsEmpty(faq)) {
            throw new IllegalArgumentException("question and answer are required fields!");
        }
        return faqRepository.save(faq);
    }

    private Boolean faqQuestionOrAnswerIsEmpty(FAQ faq) {
        return faq.getQuestion() == null || faq.getAnswer() == null ||
               faq.getQuestion().isEmpty() || faq.getAnswer().isEmpty();
    }

    public Optional<FAQ> getFAQ(Long id) {
        return faqRepository.findById(id);
    }

     public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public List<FAQ> searchFAQs(String query) {
        List<String> words = Arrays.stream(query.toLowerCase().split("\\s+"))
                                   .filter(word -> word.length() > 1)
                                   .collect(Collectors.toList());

        return faqRepository.findAll().stream()
            .filter(faq -> words.stream().anyMatch(word -> matchesFAQ(faq, word)))
            .collect(Collectors.toList());
    }

    private boolean matchesFAQ(FAQ faq, String word) {
        return containsOrSimilar(faq.getQuestion(), word) ||
               containsOrSimilar(faq.getAnswer(), word) ||
               faq.getTags().stream().anyMatch(tag -> containsOrSimilar(tag.getName(), word));
    }

    private boolean containsOrSimilar(String text, String word) {
        text = text.toLowerCase();
        return text.contains(word) || isSimilar(text, word);
    }

    private boolean isSimilar(String text, String word) {
        int distance = LevenshteinDistance.computeLevenshteinDistance(text, word);
        return distance <= Math.max(1, word.length() / 3);  
    }
    
   
    public Optional<FAQ> updateFAQ(Long id, FAQ updatedFAQ) {
        return faqRepository.findById(id).map(existingFAQ -> {
            existingFAQ.setQuestion(updatedFAQ.getQuestion());
            existingFAQ.setAnswer(updatedFAQ.getAnswer());
            existingFAQ.setTags(updatedFAQ.getTags());
            return faqRepository.save(existingFAQ);
        });
    }

    
    
    
}
