package com.example.faq_backend_api.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.interfaces.UserRepository;
import com.example.faq_backend_api.api.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Benutzername existiert bereits.";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        userRepository.save(user);
        return "Benutzer erfolgreich registriert!";
    }

    @PostMapping("/login")
    public String login(Authentication authentication) {
        return "Willkommen, " + authentication.getName() + "!";
    }
}