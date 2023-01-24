package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.security.AuthCredentials;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> authenticate(AuthCredentials loginRequest);

    ResponseEntity<?> register(User signUpRequest);
}
