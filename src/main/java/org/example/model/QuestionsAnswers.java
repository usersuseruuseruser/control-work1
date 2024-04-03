package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "questions_answers")
public class QuestionsAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionsAnswerId;
    private Long questionId;
    private Long answerId;

    public long getQuestionsAnswerId() {
        return questionsAnswerId;
    }

    public void setQuestionsAnswerId(long questionsAnswerId) {
        this.questionsAnswerId = questionsAnswerId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
