package org.example.controller;

import com.cloudinary.Cloudinary;
import org.example.Utils.CloudinaryUtil;
import org.example.Utils.UserValidator;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.annotation.MultipartConfig;

@Controller()
public class UserInfoEdit {
    private final UserService userService;
private final Cloudinary cloudinary = CloudinaryUtil.getInstance();

    public UserInfoEdit(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/edit")
    public String editUserInfo(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getByName(username));
        return "editUserAccountInfo";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/edit")
    public String editUserInfo(String name, String selfInfo, String email, Model model) {
        UserDto dto = userService.getByName(name);
        String errorMessage = "";
        if (!name.equals("") && (!UserValidator.validateName(name) || userService.getByName(name) != null)) {
            errorMessage = "не пройдена проверка по имени." +
                    " или такой пользователь уже существует или имя не в пределах от 2 до 50 символов";
        }
        else if (!email.equals("") && !UserValidator.validateEmail(email)) {
            errorMessage = "не пройдена проверка по email. попробуйте другой email";
        }
        else if (!selfInfo.equals("") && !UserValidator.validateSelfInfo(selfInfo)) {
            errorMessage = "не пройдена проверка описания своего профиля. оно должно быть меньше 500 символов.";
        }
        if (!errorMessage.equals("")) {
            model.addAttribute("errorMessage", errorMessage);
            return "editUserAccountInfo";
        }

        if (!name.equals("") && UserValidator.validateName(name) && userService.getByName(name) == null) {
            dto.setName(name);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Authentication newAuth = new UsernamePasswordAuthenticationToken(name, auth.getCredentials(), auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }
        if (!email.equals("") && UserValidator.validateEmail(email)) {
            dto.setEmail(email);
        }
        if (!selfInfo.equals("") && UserValidator.validateSelfInfo(selfInfo)) {
            dto.setSelfInfo(selfInfo);
        }
        userService.updateUser(name, dto);
        return "redirect:/users/" + name;
    }
}
