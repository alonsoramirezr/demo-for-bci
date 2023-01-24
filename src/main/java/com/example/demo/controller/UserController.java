package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseError;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User userRequest) {
        Response response = userService.createUser(userRequest);
        if(response.getCode() == 200){
            return ResponseEntity.ok(new Response(200, "OK", userRequest));
        } else {
            return new ResponseEntity<>(new ResponseError(400, response.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") String userId, @Valid @RequestBody UserUpdateRequest userRequest) {
        Response response = userService.updateUser(userRequest, userId);
        if(response.getCode() == 200){
            return ResponseEntity.ok(new Response(response.getCode(), "OK", response.getUsuario()));
        } else {
            return new ResponseEntity<>(new ResponseError(response.getCode(), response.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
