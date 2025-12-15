package com.tund.identity.service.mapper;

import com.tund.identity.service.dto.request.UserCreateRequest;
import com.tund.identity.service.dto.request.UserUpdateRequest;
import com.tund.identity.service.dto.response.UserResponse;
import com.tund.identity.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
}
