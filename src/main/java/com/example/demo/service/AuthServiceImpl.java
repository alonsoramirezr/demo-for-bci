package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseError;
import com.example.demo.security.AuthCredentials;
import com.example.demo.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> authenticate(AuthCredentials loginRequest) {
        ResponseEntity<?> response = null;
        try{
            Optional<User> userFromBd = userRepository.findOneByEmail(loginRequest.getEmail());
            if(userFromBd.isPresent()){
                User userCaptured = userFromBd.get();
                if(userCaptured.isActive()){
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    String jwt = userRepository.getToken(loginRequest.getEmail());
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    userCaptured.setLastLogin(Calendar.getInstance().getTime());
                    userRepository.save(userCaptured);
                    response = ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
                } else {
                    return new ResponseEntity<>(new ResponseError(401, "user is not active"),
                            HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(new ResponseError(401, "user doesn't exist"),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseError(401, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
       return response;
    }

    @Override
    public ResponseEntity<?> register(User signUpRequest) {
        Response response = userService.createUser(signUpRequest);
        if(response.getCode() == 200){
            return ResponseEntity.ok(new Response(200, "OK", signUpRequest));
        } else {
            return new ResponseEntity<>(new ResponseError(400, response.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
