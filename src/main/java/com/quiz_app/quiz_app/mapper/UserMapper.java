package com.quiz_app.quiz_app.mapper;

import com.quiz_app.quiz_app.dto.request.UserCreationRequest;
import com.quiz_app.quiz_app.dto.request.UserUpdateRequest;
import com.quiz_app.quiz_app.dto.response.UserResponse;
import com.quiz_app.quiz_app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
}
