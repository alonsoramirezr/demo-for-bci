package com.example.demo.service;

import com.example.demo.model.Phone;
import com.example.demo.model.User;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void createUserSucessfullyTest(){
        Response response = userService.createUser(createUser());
        assertEquals(200, response.getCode());
    }

    @Test
    void createUserWithTheSameEmailErrorTest(){
        User user1 = createUser();
        User user2 = createUser();
        userService.createUser(user1);
        Response response = userService.createUser(user2);
        assertEquals("email already exists", response.getMessage());
    }

    @Test
    void createUserWithInvalidEmailTest(){
        User user = createUser();
        user.setEmail("12345");
        Response response = userService.createUser(user);
        assertEquals("email or password is not valid", response.getMessage());
    }

    @Test
    void createUserWithInvalidPasswordTest(){
        User user = createUser();
        user.setPassword("123");
        Response responsePassword = userService.createUser(user);
        assertEquals("email or password is not valid", responsePassword.getMessage());
    }

    @Test
    void updateUserSucessfullyTest(){
        Response response = userService.createUser(createUser());
        String userId = response.getUsuario().getId();
        Response responseUpdateSucess = userService.updateUser(createUpdaterequest(), userId);
        assertEquals(200, responseUpdateSucess.getCode());
    }

    @Test
    void updateUserNotFoundOnDbTest(){
        String userId = "randomid";
        Response response = userService.updateUser(createUpdaterequest(), userId);
        assertEquals(500, response.getCode());
        assertEquals("user doesn't exist", response.getMessage());
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
}
