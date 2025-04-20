package com.spring.ai.intro.services;

import com.spring.ai.intro.model.Answer;
import com.spring.ai.intro.model.Question;
import org.springframework.stereotype.Service;

@Service
public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);
}
