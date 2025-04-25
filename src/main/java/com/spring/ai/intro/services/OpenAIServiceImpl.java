package com.spring.ai.intro.services;


import com.spring.ai.intro.model.Answer;
import com.spring.ai.intro.model.GetCapitalRequest;
import com.spring.ai.intro.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

   private final ChatModel chatModel;

   @Value("classpath:templates/get-capital-prompt.st")
   private Resource getCapitalPrompt;

   @Value("classpath:templates/get-capital-with-info-prompt.st")
   private Resource getCapitalWithInfoPrompt;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getText();
    }


    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getText());
    }

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getText());
    }

    /**
     * Gets the capital of a state or country along with additional information such as
     * population, location, language, and currency.
     * 
     * @param getCapitalRequest the request containing the state or country name
     * @return an Answer object containing the capital information in a structured format
     */
    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getText());
    }


}
