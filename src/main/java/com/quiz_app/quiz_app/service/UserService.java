package com.quiz_app.quiz_app.service;

import com.quiz_app.quiz_app.dto.request.UserCreationRequest;
import com.quiz_app.quiz_app.dto.request.UserUpdateRequest;
import com.quiz_app.quiz_app.dto.response.ApiResponse;
import com.quiz_app.quiz_app.dto.response.UserResponse;
import com.quiz_app.quiz_app.entity.User;
import com.quiz_app.quiz_app.enums.Roles;
import com.quiz_app.quiz_app.exception.AppException;
import com.quiz_app.quiz_app.exception.ErrorCode;
import com.quiz_app.quiz_app.mapper.UserMapper;
import com.quiz_app.quiz_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserResponse createRequest(UserCreationRequest request) {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new AppException(ErrorCode.USER_EXSTED);
            }

        User user = userMapper.toUser(request);
        System.out.println("hello 3");

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {


//            HashSet<String> roles = new HashSet<>();
//            roles.add(Roles.USER.name());
//            user.setRoles(roles);

                return userMapper.toUserResponse(userRepository.save(user));
            } catch(Exception e){
                System.out.println("erorherer");
                System.out.print(e);
            return userMapper.toUserResponse(userRepository.save(user));

        }
    }

    // only admin is allowed to access
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String uid) {
        System.out.print("hello herer");
        return userMapper.toUserResponse(userRepository.findById(uid).orElseThrow(() -> new RuntimeException("user not found")));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_EXSTED));

        return userMapper.toUserResponse(user);
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
