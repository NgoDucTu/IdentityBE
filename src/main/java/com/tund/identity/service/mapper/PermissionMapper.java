package com.tund.identity.service.mapper;

import org.mapstruct.Mapper;

import com.tund.identity.service.dto.request.PermissionRequest;
import com.tund.identity.service.dto.response.PermissionResponse;
import com.tund.identity.service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
