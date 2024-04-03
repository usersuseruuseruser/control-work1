package org.example.repository;

import org.example.model.QuestionsAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsAnswersRepository extends JpaRepository<QuestionsAnswers, Long>{
}
