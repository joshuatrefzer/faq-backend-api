package com.example.faq_backend_api.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.faq_backend_api.api.interfaces.UserRepository;
import com.example.faq_backend_api.api.model.LoginResponse;
import com.example.faq_backend_api.api.model.SignUpResponse;
import com.example.faq_backend_api.api.model.User;
import com.example.faq_backend_api.api.model.UserLoginRequest;
import com.example.faq_backend_api.service.JwtService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private JwtService jwtService;



    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            SignUpResponse errorResponse = new SignUpResponse(false, "User with this username already exists");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        userRepository.save(user);

        SignUpResponse successResponse = new SignUpResponse(true, "User successful registered");
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            String token = jwtService.generateToken(user.get().getUsername());
            LoginResponse loginResponse = new LoginResponse(true, token);
            return ResponseEntity.ok(loginResponse); 
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Invalid credentials"));
    }
}