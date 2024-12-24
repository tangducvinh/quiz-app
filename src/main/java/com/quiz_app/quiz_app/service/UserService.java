package com.quiz_app.quiz_app.service;

import com.quiz_app.quiz_app.dto.request.UserCreationRequest;
import com.quiz_app.quiz_app.dto.request.UserUpdateRequest;
import com.quiz_app.quiz_app.dto.response.UserResponse;
import com.quiz_app.quiz_app.entity.User;
import com.quiz_app.quiz_app.exception.AppException;
import com.quiz_app.quiz_app.exception.ErrorCode;
import com.quiz_app.quiz_app.mapper.UserMapper;
import com.quiz_app.quiz_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User createRequest(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXSTED);
        }

        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String uid) {
        return userMapper.toUserResponse(userRepository.findById(uid).orElseThrow(() -> new RuntimeException("user not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
