package com.viz.visualdataboard.controller;

import com.viz.visualdataboard.domain.User;
import com.viz.visualdataboard.dto.request.LoginRequest;
import com.viz.visualdataboard.service.UserService;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Cookie login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/join")
    public void signUp(@RequestBody User user) {
        userService.join(user);
    }
}

