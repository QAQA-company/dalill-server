package com.qaqa.dalill.user.controller;

import com.qaqa.dalill.user.model.response.CustomResponseEntity;
import com.qaqa.dalill.user.model.user.UserLoginRequest;
import com.qaqa.dalill.user.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public CustomResponseEntity signupUser(@Valid @RequestBody UserLoginRequest request) {
        userService.signup(request);
        return CustomResponseEntity.builder()
                .status(201)
                .message("User registered")
                .build();
    }

    @PostMapping("/login")
    public CustomResponseEntity loginUser(@RequestBody UserLoginRequest request) {
        if (userService.verify(request)) {
            return CustomResponseEntity.builder()
                    .status(200)
                    .message(userService.login(request))
                    .build();
        }
        return CustomResponseEntity.builder()
                .status(401)
                .message("Invalid username or password")
                .build();
    }
}
