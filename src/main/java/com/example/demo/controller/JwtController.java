package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.AuthCredentials;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class JwtController {

    @Autowired
    AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthCredentials loginRequest) {
        return authService.authenticate(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody User signUpRequest) {
        return authService.register(signUpRequest);
    }
}
