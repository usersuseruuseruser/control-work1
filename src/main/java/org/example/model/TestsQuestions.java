package org.example.model;

@javax.persistence.Entity
@javax.persistence.Table(name = "tests_questions")
public class TestsQuestions {
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long testQuestionId;
    private Long testId;
    private Long questionId;

    public long getTestQuestionId() {
        return testQuestionId;
    }

    public void setTestQuestionId(long tests_questions_id) {
        this.testQuestionId = tests_questions_id;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long test_id) {
        this.testId = test_id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long question_id) {
        this.questionId = question_id;
    }
}
