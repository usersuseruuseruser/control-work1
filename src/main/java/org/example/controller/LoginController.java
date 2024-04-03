package org.example.controller;

import org.example.security.AuthProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.example.model.User_.login;
import static org.example.model.User_.password;

@Controller
public class LoginController {
    private final AuthProvider authenticationManager;

    public LoginController(AuthProvider authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
            Authentication auth = authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/main";
        } catch (Exception e){
            return "redirect:/exception?message=" + e.getMessage();
        }

    }
}
