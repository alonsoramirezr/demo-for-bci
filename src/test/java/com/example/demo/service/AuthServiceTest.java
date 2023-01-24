package com.example.demo.service;

import com.example.demo.model.Phone;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.Response;
import com.example.demo.security.AuthCredentials;
import com.example.demo.security.TokenUtils;
import com.example.demo.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Test
    void registerSucessfullyTest(){
        ResponseEntity<?> response = authService.register(createUser());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void registerWithErrorTest(){
        ResponseEntity<?> response = authService.register(null);
        assertEquals(400, response.getStatusCodeValue());
    }
    private User createUser(){
        User user = new User();
        user.setName("John");
        user.setEmail("johndoe@gmail.com");
        user.setPassword("P@ssw0rd258465");
        user.setPhones(createPhonesList());
        return user;
    }

    private UserUpdateRequest createUpdaterequest(){
        UserUpdateRequest request = new UserUpdateRequest();
        request.setName("Foo");
        request.setEmail("foorbar@gmail.com");
        request.setPassword("P@ssw0rd234");
        request.setPhones(createPhonesList());
        request.setActive(true);
        return request;
    }

    private List<Phone> createPhonesList(){
        Phone p1 = new Phone(1,2,3,4);
        Phone p2 = new Phone(5,6,7,8);
        Phone p3 = new Phone(9,10,11,12);
        List<Phone> phones = Arrays.asList(p1, p2, p3);
        return phones;
    }

    private User saveUser(){
        User user = new User();
        user.setName("John");
        user.setEmail("johndoe@gmail.com");
        user.setPassword("P@ssw0rd258465");
        user.setPhones(createPhonesList());
        String jwt = TokenUtils.createToken(user.getEmail(), user.getPassword());
        String token = utils.generateUUID();
        user.setId(token);
        user.setCreated(Calendar.getInstance().getTime());
        user.setModified(Calendar.getInstance().getTime());
        user.setLastLogin(Calendar.getInstance().getTime());
        user.setActive(true);
        user.setToken(jwt);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
}
