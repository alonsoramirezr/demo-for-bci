package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.UserUpdateRequest;
import com.example.demo.response.Response;
import com.example.demo.security.TokenUtils;
import com.example.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private Utils utils;

    @Override
    public List getUsers() {
       List<User> users = userRepository.findAll();
       return users;
    }

    @Override
    public Response createUser(User user) {

        Response response = new Response();

        try {
            if (userRepository.emailExist(user.getEmail()) >= 1) {
                response.setCode(400);
                response.setMessage("email already exists");
                return response;
            }
            if (!(utils.validateEmail(user.getEmail()) && utils.validatePassword(user.getPassword()))) {
                response.setCode(400);
                response.setMessage("email or password is not valid");
                return response;
            }
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
            response.setCode(200);
            response.setUsuario(user);
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateUser(UserUpdateRequest request, String id) {
        Response response = new Response();

        try {
            Optional<User> userFromDb = userRepository.findById(id);
            if (!userFromDb.isPresent()) {
                response.setCode(400);
                response.setMessage("user doesn't exist");
                return response;
            }
            User userCaptured = userFromDb.get();
            if (userRepository.emailExist(request.getEmail()) >= 1) {
                Optional<User> userExists = userRepository.findOneByEmail(request.getEmail());
                if (userExists.isPresent()) {
                    User userObtainedWithEmail = userExists.get();
                    if (!userObtainedWithEmail.getId().equalsIgnoreCase(userCaptured.getId())) {
                        response.setCode(400);
                        response.setMessage("email is already taken");
                        return response;
                    }
                } else {
                    response.setCode(400);
                    response.setMessage("email is already taken");
                    return response;
                }
            }

            if (!(utils.validateEmail(request.getEmail()) && utils.validatePassword(request.getPassword()))) {
                response.setCode(400);
                response.setMessage("email or password is not valid");
                return response;
            }
            userCaptured.setName(request.getName());
            userCaptured.setEmail(request.getEmail());
            userCaptured.setPhones(request.getPhones());
            userCaptured.setModified(Calendar.getInstance().getTime());
            userCaptured.setActive(request.isActive());
            userCaptured.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(userCaptured);
            response.setCode(200);
            response.setUsuario(userCaptured);

        } catch (Exception e) {
            response.setCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
