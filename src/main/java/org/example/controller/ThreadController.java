package org.example.controller;

import org.example.model.Thread;
import org.example.model.ThreadMessage;
import org.example.repository.ThreadMessageRepository;
import org.example.repository.ThreadRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller()
public class ThreadController {
    private final ThreadMessageRepository threadMessageRepository;

    public ThreadController(ThreadMessageRepository threadMessageRepository) {
        this.threadMessageRepository = threadMessageRepository;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/thread")
    public String postThread(@RequestParam String username, @RequestParam String text, @RequestParam int threadId) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ThreadMessage threadMessage = new ThreadMessage();
        threadMessage.setUsername(username);
        threadMessage.setText(text);
        threadMessage.setData(timestamp);
        threadMessage.setThreadId(threadId);
        ThreadMessage threadMessage1 = threadMessageRepository.save(threadMessage);
        return "forum";
    }
}
