package org.example.repository;

import org.example.model.Thread;
import org.example.model.ThreadMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
ThreadRepository extends JpaRepository<Thread, Long> {
}
