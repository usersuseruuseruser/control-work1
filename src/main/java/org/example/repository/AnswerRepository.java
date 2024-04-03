package org.example.repository;

import org.example.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
    public Answer getAnswerByAnswerId(Long answerId);
    List<Answer> getAnswerByQuestionIdIn(List<Long> questionIds);
}
