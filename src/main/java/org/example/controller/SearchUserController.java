package org.example.controller;

import org.example.dto.UserDto;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchUserController {

    private final UserService userService;

    public SearchUserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/searchUser")
    public UserDto searchUser(@RequestParam String username) {
        if(username != null && !username.trim().isEmpty()) {
            return userService.getByName(username);
        } else {
            return null;
        }
    }
}
