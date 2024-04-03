package org.example.repository;

import org.example.model.Question;
import org.example.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    public Test getTestByTestId(Long testId);
}
