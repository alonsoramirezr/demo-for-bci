package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.Response;

import java.util.List;

public interface UserService {
    List getUsers();

    Response createUser(User user);

    Response updateUser(UserUpdateRequest request, String id);
}
