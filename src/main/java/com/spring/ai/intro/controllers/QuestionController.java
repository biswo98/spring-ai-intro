package com.spring.ai.intro.controllers;

import com.spring.ai.intro.model.Answer;
import com.spring.ai.intro.model.Question;
import com.spring.ai.intro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer getAnswer(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
}
