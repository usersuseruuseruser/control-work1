package org.example.controller;

import org.example.Utils.UserValidator;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class RegistrationController {
    UserService userService;
    private final static PasswordEncoder encoder = new BCryptPasswordEncoder();
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String login,
                                 @RequestParam String password) {
        String errorMessage = "";

        if(userService.get(login) != null) {
            errorMessage = "Логин уже существует.";
        } else if (userService.getByName(name) != null) {
            errorMessage = "Имя пользователя уже существует.";
        } else if (!UserValidator.validateName(name) || Objects.equals("", name)) {
            errorMessage = "Имя не должно быть пустым, а также долнжо быть в пределах от 2 до 50 символов.";
        } else if (!UserValidator.validateLogin(login) || Objects.equals("", login)) {
            errorMessage = "Логин не должен быть пустым, а также должен быт ьв пределах от 3 до 30 символов.";
        } else if (!UserValidator.validatePassword(password) || Objects.equals("", password)) {
            errorMessage = "Пароль не должен быть пустым а также быть в пределах от 8 до 100 символов";
        }

        if (!errorMessage.isEmpty()) {
            return new ModelAndView("exception", "message", errorMessage);
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setLogin(login);
        newUser.setPassword(encoder.encode(password));
        try {
            userService.save(newUser);
        } catch (Exception e){
            return new ModelAndView("exception", "message", e.getMessage());
        }

        return new ModelAndView("redirect:/main");
    }
}
