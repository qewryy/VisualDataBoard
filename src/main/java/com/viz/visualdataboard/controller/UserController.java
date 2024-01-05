package com.viz.visualdataboard.controller;

import com.viz.visualdataboard.dto.request.LoginRequest;
import com.viz.visualdataboard.dto.request.SignUpRequest;
import com.viz.visualdataboard.dto.response.UserResponse;
import com.viz.visualdataboard.service.UserService;
import com.viz.visualdataboard.service.UserDetailsServiceImpl;
import com.viz.visualdataboard.service.FileStorageService;
import jakarta.servlet.http.Cookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final FileStorageService fileStorageService;

    public UserController(UserService userService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/login")
    public Cookie login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/join")
    public void signUp(@RequestBody SignUpRequest signUpRequest){
        String Filename = fileStorageService.storeFile(signUpRequest.getFile());
        signUpRequest.setFilename(Filename);
        userService.join(signUpRequest);
    }

    @GetMapping("/me")
    public UserResponse getMe(@AuthenticationPrincipal UserDetailsServiceImpl userDetails) {
        return userService.getMe(userDetails);
    }
}

