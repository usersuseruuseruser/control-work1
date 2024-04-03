package org.example.repository;

import org.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
public Question getQuestionByAnswerId(Long answerId);
List<Question> getQuestionByTestTestId(Long testId);
}
