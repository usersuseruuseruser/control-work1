package org.example.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.Utils.CloudinaryUtil;
import org.example.model.Thread;
import org.example.model.ThreadMessage;
import org.example.repository.ThreadMessageRepository;
import org.example.repository.ThreadMessagesRepository;
import org.example.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller()
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024 * 8,
        maxRequestSize = 5 * 1024 * 1024 * 10
)
public class ForumController {

    private final ThreadRepository threadRepository;

    private final ThreadMessageRepository threadMessageRepository;

    private final Cloudinary cloudinary;

    public ForumController(ThreadRepository threadRepository, ThreadMessageRepository threadMessageRepository) {
        this.threadRepository = threadRepository;
        this.threadMessageRepository = threadMessageRepository;
        this.cloudinary= CloudinaryUtil.getInstance();
    }
    @GetMapping("/forum")
    public String getAllThreads(Model model) {
        List<Thread> threadList = threadRepository.findAll();
        model.addAttribute("threads", threadList);
        return "forum";
    }
    @GetMapping("/forum/{id}")
    public String getThreadById(@PathVariable long id, Model model) {
        Thread thread = threadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Такого треда нет"));
        List<ThreadMessage> messages = threadMessageRepository.findAllMessagesByThreadId(id);
        model.addAttribute("headInfo", thread);
        model.addAttribute("messages", messages);
        return "thread";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping()
    public String addMessage(@RequestParam("threadTitle") String title,
                             @RequestParam("threadDescription") String description,
                             @RequestPart("threadImage") MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        Thread thread = new Thread();
        thread.setTitle(title);
        thread.setDescription(description);
        thread.setImageUrl(imageUrl);
        threadRepository.save(thread);
        return "redirect:/forum";
    }
}
