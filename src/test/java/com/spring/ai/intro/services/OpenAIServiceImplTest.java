package com.spring.ai.intro.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {


    @Autowired
    private OpenAIService openAIService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAnswer() {
        String answer = openAIService.getAnswer("Write a python script to output numbers from 1 to 100.");
        System.out.println("got the answer");
        System.out.println(answer);


    }
}