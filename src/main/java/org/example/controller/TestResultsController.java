package org.example.controller;

import org.example.model.Question;
import org.example.repository.AnswerRepository;
import org.example.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller()
public class TestResultsController {
    AnswerRepository answerRepository;

    QuestionRepository questionRepository;

    public TestResultsController(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }
    @PostMapping("/testResults")
    public String getTestResults(@RequestParam Map<String, String> params,
                                 Model model) {
        List<Long> answerIds = new ArrayList<>();
        int totalAnswers = Integer.parseInt(params.get("questions_length"));
        int rightAnswers = 0;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = entry.getKey();
            if (paramName.startsWith("answerGroup_")) {
                String answerId = entry.getValue();
                answerIds.add(Long.valueOf(answerId));
            }
        }

        for (Long id : answerIds) {
            if (id.equals(questionRepository.getQuestionByAnswerId(answerRepository.getAnswerByAnswerId(id).getQuestionId()).getAnswerId())) {
                rightAnswers++;
            }
        }

        model.addAttribute("results", rightAnswers + "/" + totalAnswers);
        return "TestResults";

    }
}
