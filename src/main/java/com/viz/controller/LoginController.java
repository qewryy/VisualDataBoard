package com.viz.controller;

import com.viz.dto.request.LoginRequest;
import com.viz.service.UserService;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Cookie login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

