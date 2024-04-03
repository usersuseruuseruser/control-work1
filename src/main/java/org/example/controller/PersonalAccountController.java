package org.example.controller;

import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping()
@Controller
public class PersonalAccountController {
    private final UserService userService;

    public PersonalAccountController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserDto> listUserDto = userService.getAll();
        model.addAttribute("users", listUserDto);
        return "profile";
    }
    @PostMapping("/users")
    public String sortUsers(@RequestParam String sorting, Model model) {
        List<UserDto> listUserDto;
        switch (sorting) {
            case "byPhoto":
                listUserDto = userService.getAll().stream()
                        .filter(user -> user.getProfilePictureUrl() != null && !user.getProfilePictureUrl().isEmpty())
                        .collect(Collectors.toList());
                break;
            case "byEmail":
                listUserDto = userService.getAll().stream()
                        .filter(user -> user.getEmail() != null && !user.getEmail().isEmpty())
                        .collect(Collectors.toList());
                break;
            case "byName":
                listUserDto = userService.getAll().stream()
                        .sorted(Comparator.comparing(UserDto::getName))
                        .collect(Collectors.toList());
                break;
            default:
                listUserDto = userService.getAll();
        }
        model.addAttribute("users", listUserDto);
        return "profile";
    }
    @GetMapping("/users/{userName}")
    public String getUserByName(@PathVariable String userName, Model model) {
        UserDto userDto = userService.getByName(userName);
        if (userDto == null) {
            return "exception";
        }
        model.addAttribute("users", Collections.singletonList(userDto));
        return "profile";
    }
}
