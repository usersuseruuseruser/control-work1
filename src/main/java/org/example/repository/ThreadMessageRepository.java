package org.example.repository;

import org.example.model.ThreadMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadMessageRepository extends JpaRepository<ThreadMessage, Long> {
    List<ThreadMessage> findAllMessagesByThreadId(long threadId);
}
