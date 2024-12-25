package com.quiz_app.quiz_app.controller;


import com.quiz_app.quiz_app.dto.request.UserCreationRequest;
import com.quiz_app.quiz_app.dto.request.UserUpdateRequest;
import com.quiz_app.quiz_app.dto.response.ApiResponse;
import com.quiz_app.quiz_app.dto.response.UserResponse;
import com.quiz_app.quiz_app.entity.User;
import com.quiz_app.quiz_app.mapper.UserMapper;
import com.quiz_app.quiz_app.repository.UserRepository;
import com.quiz_app.quiz_app.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.createRequest(request)).build();
    }

    @GetMapping("/users")
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(s -> log.info(s.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder().result(userService.getUsers()).build();
    }

    @GetMapping("/user/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/user/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/user/{userId}")
    void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }
}
