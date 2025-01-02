package com.quiz_app.quiz_app.mapper;

import com.quiz_app.quiz_app.dto.request.PermissionRequest;
import com.quiz_app.quiz_app.dto.response.PermissionResponse;
import com.quiz_app.quiz_app.entity.Permission;
import org.mapstruct.Mapper;

import javax.swing.*;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
