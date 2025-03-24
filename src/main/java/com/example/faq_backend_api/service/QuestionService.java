package com.example.faq_backend_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.faq_backend_api.api.interfaces.QuestionRepository;
import com.example.faq_backend_api.api.model.Question;


@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

     public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        if (this.questionIsEmpty(question)) {
            throw new IllegalArgumentException("The question must be a filled value");
        }
        return questionRepository.save(question);
    }

    public boolean deleteQuestion(Long id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Boolean questionIsEmpty(Question question) {
        String myQuestion = question.getQuestion();
        return myQuestion == null || myQuestion.isEmpty() || myQuestion.length() <= 5 ;
    }

}
