package com.quiz_app.quiz_app.mapper;

import com.quiz_app.quiz_app.dto.request.RoleRequest;
import com.quiz_app.quiz_app.dto.response.RoleResponse;
import com.quiz_app.quiz_app.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    @Mapping(source = "permissions", target = "permissions")
    RoleResponse toRoleResponse(Role role);
}
