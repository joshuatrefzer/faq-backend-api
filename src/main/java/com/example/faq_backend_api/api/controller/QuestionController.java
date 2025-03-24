package com.example.faq_backend_api.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.model.DeleteResponse;
import com.example.faq_backend_api.api.model.Question;
import com.example.faq_backend_api.service.QuestionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return questions.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<Question> postQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteQuestion(@PathVariable Long id) {
        boolean deleted = questionService.deleteQuestion(id);

        if (deleted) {
            return ResponseEntity.ok(new DeleteResponse(true, "Question deleted successfully"));
        } else {
            return ResponseEntity.status(404).body(new DeleteResponse(false, "Question not found"));
        }
    }

}
