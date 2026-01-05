package com.tund.identity.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tund.identity.service.dto.request.RoleRequest;
import com.tund.identity.service.dto.response.RoleResponse;
import com.tund.identity.service.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
