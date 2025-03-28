package com.example.faq_backend_api.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.example.faq_backend_api.api.interfaces.QuestionRepository;
import com.example.faq_backend_api.api.model.Question;
import com.example.faq_backend_api.service.QuestionService;

@ExtendWith(MockitoExtension.class) 
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository; 

    @InjectMocks
    private QuestionService questionService; 

    private Question question1;
    private Question question2;

    @BeforeEach
    void setUp() {
        question1 = new Question();
        question1.setId(1L);
        question1.setQuestion("What is Java?");

        question2 = new Question();
        question2.setId(2L);
        question2.setQuestion("How does Spring Boot work?");
    }

    @Test
    void testGetAllQuestions() {
        when(questionRepository.findAll()).thenReturn(Arrays.asList(question1, question2));

        List<Question> questions = questionService.getAllQuestions();

        assertEquals(2, questions.size());
        verify(questionRepository, times(1)).findAll(); 
    }

    @Test
    void testAddQuestion_Success() {
        when(questionRepository.save(any(Question.class))).thenReturn(question1);

        Question savedQuestion = questionService.addQuestion(question1);

        assertNotNull(savedQuestion);
        assertEquals("What is Java?", savedQuestion.getQuestion());
        verify(questionRepository, times(1)).save(question1);
    }

    @Test
    void testAddQuestion_Failure_EmptyQuestion() {
        String expectedValue = "The question must be a filled value";
        
        Question invalidQuestion = new Question();
        invalidQuestion.setQuestion("abc"); // is too short, less than 3 characters

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            questionService.addQuestion(invalidQuestion);
        });

        assertEquals(expectedValue, exception.getMessage());
        verify(questionRepository, never()).save(any()); 
    }

    @Test
    void testDeleteQuestion_Success() {
        when(questionRepository.existsById(1L)).thenReturn(true);

        boolean result = questionService.deleteQuestion(1L);

        assertTrue(result);
        verify(questionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteQuestion_Failure_NotFound() {
        when(questionRepository.existsById(99L)).thenReturn(false);

        boolean result = questionService.deleteQuestion(99L);

        assertFalse(result);
        verify(questionRepository, never()).deleteById(anyLong());
    }
}
