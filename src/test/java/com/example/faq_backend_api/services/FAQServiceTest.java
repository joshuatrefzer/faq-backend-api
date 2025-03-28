package com.example.faq_backend_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.faq_backend_api.api.interfaces.FAQRepository;
import com.example.faq_backend_api.api.model.FAQ;
import com.example.faq_backend_api.api.model.Tag;
import com.example.faq_backend_api.service.FAQService;
import com.example.faq_backend_api.service.TagService;


@ExtendWith(MockitoExtension.class)
public class FAQServiceTest {
    @Mock // Mock für Repository
    private FAQRepository faqRepository;

    @Mock // Mock für TagService
    private TagService tagService;

    @InjectMocks // Erstellt FAQService mit den Mock-Abhängigkeiten
    private FAQService faqService;

    private FAQ mockFAQ;

    @BeforeEach
    void setUp() {
        mockFAQ = new FAQ();
        mockFAQ.setId(1L);
        mockFAQ.setQuestion("Was ist Spring Boot?");
        mockFAQ.setAnswer("Spring Boot ist ein Framework für Java.");
        mockFAQ.setTags(new HashSet<>());
    }

    @Test
    void testAddFAQ_Success() {
        when(faqRepository.save(any(FAQ.class))).thenReturn(mockFAQ);

        FAQ savedFAQ = faqService.addFAQ(mockFAQ);

        assertNotNull(savedFAQ);
        assertEquals("Was ist Spring Boot?", savedFAQ.getQuestion());
        assertEquals("Spring Boot ist ein Framework für Java.", savedFAQ.getAnswer());
    }

    @Test
    void testAddFAQ_ThrowsExceptionWhenQuestionIsEmpty() {
        FAQ invalidFAQ = new FAQ();
        invalidFAQ.setQuestion("");
        invalidFAQ.setAnswer("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            faqService.addFAQ(invalidFAQ);
        });

        assertEquals("question and answer are required fields!", exception.getMessage());
    }

    @Test
    void testGetFAQ_ReturnsFAQ() {
        when(faqRepository.findById(1L)).thenReturn(Optional.of(mockFAQ));

        Optional<FAQ> result = faqService.getFAQ(1L);

        assertTrue(result.isPresent());
        assertEquals("Was ist Spring Boot?", result.get().getQuestion());
    }

    @Test
    void testGetAllFAQs_ReturnsList() {
        List<FAQ> faqList = Arrays.asList(mockFAQ, new FAQ());
        when(faqRepository.findAll()).thenReturn(faqList);

        List<FAQ> result = faqService.getAllFAQs();

        assertEquals(2, result.size());
    }

    @Test
    void testAddTagsToFAQ() {
        Tag mockTag = new Tag();
        mockTag.setName("Spring");

        when(faqRepository.findById(1L)).thenReturn(Optional.of(mockFAQ));
        when(tagService.getTagByName("Spring")).thenReturn(Optional.of(mockTag));
        when(faqRepository.save(any(FAQ.class))).thenReturn(mockFAQ);

        FAQ updatedFAQ = faqService.addTagsToFAQ(1L, Set.of("Spring"));

        assertNotNull(updatedFAQ);
        assertEquals(1, updatedFAQ.getTags().size());
    }

    @Test
    void testSearchFAQs_Found() {
        when(faqRepository.findAll()).thenReturn(List.of(mockFAQ));

        List<FAQ> result = faqService.searchFAQs("Spring");

        assertEquals(1, result.size());
    }
    
}
