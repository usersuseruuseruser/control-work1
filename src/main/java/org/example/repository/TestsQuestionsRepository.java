package org.example.repository;

import org.example.model.TestsQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestsQuestionsRepository extends JpaRepository<TestsQuestions, Long> {

}
