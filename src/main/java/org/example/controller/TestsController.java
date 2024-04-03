package org.example.controller;

import org.example.model.Answer;
import org.example.model.Question;
import org.example.model.Test;
import org.example.repository.AnswerRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.TestRepository;
import org.example.repository.TestsQuestionsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller()
public class TestsController {
    AnswerRepository answerRepository;
    QuestionRepository questionRepository;
    TestRepository testRepository;

    public TestsController(AnswerRepository answerRepository, QuestionRepository questionRepository, TestRepository testRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    @GetMapping("tests/{id}")
    public String getTest(@PathVariable String id, Model model) {
        Test test = testRepository.getTestByTestId(Long.parseLong(id));
        List<Question> questions = questionRepository.getQuestionByTestTestId(test.getTestId());
        List<Long> questionIds = new ArrayList<>();
        for (Question question : questions) {
            questionIds.add(question.getQuestionId());
        }
        List<Answer> answers = answerRepository.getAnswerByQuestionIdIn(questionIds);
        model.addAttribute("headInfo", test);
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        return "testPage";
    }

    @GetMapping("/tests")
    public String getAllTests(Model model) {
        List<Test> tests = testRepository.findAll();
        model.addAttribute("tests", tests);
        return "tests";
    }
}
